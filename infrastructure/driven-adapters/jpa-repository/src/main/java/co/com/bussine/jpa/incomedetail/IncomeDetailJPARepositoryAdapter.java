package co.com.bussine.jpa.incomedetail;

import co.com.bussine.jpa.helper.AdapterOperations;
import co.com.bussine.jpa.income.mapper.IncomeMapper;
import co.com.bussine.jpa.incomedetail.mapper.IncomeDetailMapper;
import co.com.bussine.jpa.products.mapper.ProductsMapper;
import co.com.bussine.model.common.BusinessException;
import co.com.bussine.model.income.Income;
import co.com.bussine.model.incomedetail.IncomeDetail;
import co.com.bussine.model.incomedetail.gateways.IncomeDetailRepository;
import co.com.bussine.model.products.Products;
import co.com.bussine.model.products.gateways.ProductsRepository;
import co.com.bussine.model.utils.PaginationDTO;
import co.com.bussine.model.utils.ResponseShopSavvy;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class IncomeDetailJPARepositoryAdapter extends AdapterOperations<IncomeDetail, IncomeDetailDto, String, IncomeDetailJPARepository> implements IncomeDetailRepository {
    private final ProductsRepository productsRepository;

    public IncomeDetailJPARepositoryAdapter(IncomeDetailJPARepository repository, ObjectMapper mapper, ProductsRepository productsRepository) {
        super(repository, mapper, d -> mapper.mapBuilder(d, IncomeDetail.IncomeDetailBuilder.class).build());
        this.productsRepository = productsRepository;
    }


    @Override
    public Mono<ResponseShopSavvyDTO<List<IncomeDetail>>> getAllIncomeDetail(PaginationDTO paginationDTO) {
        return Flux.fromIterable(repository.findAll())
                .filter(ele -> ele.getStatus().equals(Boolean.TRUE))
                .map(IncomeDetailMapper::incomeDetailDtoAIncomeDetail)
                .skip(paginationDTO.getOffset())
                .take(paginationDTO.getLimit())
                .collectList()
                .zipWith(Mono.just(repository.count()))
                .map(ele -> new ResponseShopSavvyDTO<>(ele.getT1(), paginationDTO.pagination(ele.getT2())));
    }


    @Override
    public Mono<List<Products>> getIdIncomeDetails(String id) {
        return Flux.fromIterable(repository.findAll())
                .filter(ele -> ele.getIncome().getId().equals(id))
                .map(res -> {
                    res.getProducts().setStock(res.getAmount());
                    return res.getProducts();
                })
                .map(ProductsMapper::productsDtoAProduct)
                .collectList();
    }

    @Override
    public Mono<Void> createIncomeDetail(Income income, Products products, Integer quantity) {
        return productsRepository.updateProductProperties(products, Boolean.FALSE)
                .map(updatedProduct -> {
                            IncomeDetailDto incomeDetailDto = repository.save(IncomeDetailMapper.incomeDetailDtoCreate(
                                    IncomeMapper.incomeAIncomeDto(income),
                                    ProductsMapper.productsAProductsDto(updatedProduct),
                                    quantity,
                                    products.getPriceBuy(),
                                    products.getPriceSale()
                            ));
                            return IncomeDetailMapper.incomeDetailDtoAIncomeDetail(incomeDetailDto);
                        }
                )
                .then();
    }


    @Override
    public Mono<Void> invalidateIncomeDetail(String id) {
        return Flux.fromIterable(repository.findAll())
                .filter(ele -> ele.getIncome().getId().equals(id))
                .map(ele -> {
                    ele.setStatus(Boolean.FALSE);
                    ele.getProducts().setStock(ele.getAmount());
                    productsRepository.updateProductProperties(ProductsMapper.productsDtoAProduct(ele.getProducts()), Boolean.TRUE);
                    return repository.save(ele);
                })
                .then();

    }


    @Override
    public Mono<Void> updateIncomeDetails(Income income, Products products, Integer quantity) {
        return repository.findByIncomeIdAndProductsId(income.getId(), products.getId())
                .map(ele -> productsRepository.updateProductProperties(products, Boolean.FALSE)
                        .map(updatedProduct -> {
                            IncomeDetailDto incomeDetailDto = IncomeDetailMapper.incomeDetailDtoUpdate(
                                    ele,
                                    ProductsMapper.productsAProductsDto(updatedProduct),
                                    quantity,
                                    products.getPriceBuy(),
                                    products.getPriceSale()
                            );
                            repository.save(incomeDetailDto);
                            return IncomeDetailMapper.incomeDetailDtoAIncomeDetail(incomeDetailDto);
                        })
                        .then())
                .orElseGet(() -> this.createIncomeDetail(income, products, quantity));


    }

    @Override
    public Mono<ResponseShopSavvy<IncomeDetail>> cancelIncomeDetail(String idIncome, String product) {
        return repository.findByIncomeIdAndProductsId(idIncome, product).map(ele -> {
                    productsRepository.updateProductProperties(ProductsMapper.productsDtoAProduct(ele.getProducts()), Boolean.TRUE);
                    repository.deleteById(idIncome);
                    return Mono.just(new ResponseShopSavvy<>(IncomeDetailMapper.incomeDetailDtoAIncomeDetail(ele)));
                })
                .orElseThrow(() -> new BusinessException(BusinessException.Type.NO_EXISTE_ESTE_INGRESO));
    }


}
