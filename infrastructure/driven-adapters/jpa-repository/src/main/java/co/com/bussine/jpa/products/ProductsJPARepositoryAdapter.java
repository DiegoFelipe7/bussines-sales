package co.com.bussine.jpa.products;

import co.com.bussine.jpa.helper.AdapterOperations;
import co.com.bussine.jpa.products.mapper.ProductsMapper;
import co.com.bussine.model.common.BusinessException;
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
public class ProductsJPARepositoryAdapter extends AdapterOperations<Products, ProductsDto, String, ProductsJPARepository> implements ProductsRepository {

    public ProductsJPARepositoryAdapter(ProductsJPARepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Products.ProductsBuilder.class).build());
    }

    @Override
    public Mono<ResponseShopSavvyDTO<List<Products>>> getAllProducts(PaginationDTO paginationDTO) {
        return Flux.fromIterable(repository.findAllBySearchLike(paginationDTO.getSearch()))
                .filter(ele -> ele.getStatus().equals(Boolean.TRUE))
                .map(ProductsMapper::productsDtoAProduct)
                .skip(paginationDTO.getOffset())
                .take(paginationDTO.getLimit())
                .collectList()
                .zipWith(Mono.just(repository.count()))
                .map(ele -> new ResponseShopSavvyDTO<>(ele.getT1(), paginationDTO.pagination(ele.getT2())));
    }

    @Override
    public Mono<ResponseShopSavvy<Products>> getIdProduct(String id) {
        return repository.findByCode(id)
                .map(ele -> new ResponseShopSavvy<>(ProductsMapper.productsDtoAProduct(ele)))
                .map(Mono::just)
                .orElse(Mono.empty());
    }

    @Override
    public Mono<ResponseShopSavvy<Products>> createProduct(Products products) {
        if (repository.findByCode(products.getCode()).isPresent()) {
            return Mono.error(new BusinessException(BusinessException.Type.ERROR_VALOR_UNICO));
        }
        ProductsDto productsDto = repository.save(ProductsMapper.productsAProductsDto(products));
        return Mono.just(new ResponseShopSavvy<>(ProductsMapper.productsDtoAProduct(productsDto)));
    }

    @Override
    public Mono<ResponseShopSavvy<Products>> updateProduct(String code, Products products) {
        return repository.findByCode(code)
                .map(ele -> {
                    products.setId(ele.getId());
                    ProductsDto productsDto = repository.save(ProductsMapper.productsAProductsDto(products));
                    return new ResponseShopSavvy<>(ProductsMapper.productsDtoAProduct(productsDto));
                }).map(Mono::just)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.NO_EXISTE_ESTE_PRODUCTO));
    }

    @Override
    public Mono<Products> updateProductProperties(Products products, Boolean isEdit) {
        return repository.findById(products.getId())
                .map(ele -> {
                    ele.setStock(ele.updateStock(isEdit, products.getStock()));
                    ele.setPriceBuy(products.getPriceBuy());
                    ele.setPriceSale(products.getPriceSale());
                    ele.setUtility(products.utility());
                    ProductsDto productsDto = repository.save(ele);
                    return Mono.just(ProductsMapper.productsDtoAProduct(productsDto));
                })
                .orElseThrow(() -> new BusinessException(BusinessException.Type.NO_EXISTE_ESTE_PRODUCTO));
    }

    @Override
    public Mono<Long> countProducts() {
        return Flux.fromIterable(repository.findAll())
                .filter(ele->ele.getStatus().equals(Boolean.TRUE))
                .count();
    }


}
