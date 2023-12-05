package co.com.bussine.jpa.sale;

import co.com.bussine.jpa.helper.AdapterOperations;
import co.com.bussine.jpa.products.ProductsJPARepository;
import co.com.bussine.jpa.sale.mapper.SaleMapper;
import co.com.bussine.model.products.Products;
import co.com.bussine.model.sale.Sale;
import co.com.bussine.model.sale.SaleReport;
import co.com.bussine.model.sale.gateways.SaleRepository;
import co.com.bussine.model.utils.PaginationDTO;
import co.com.bussine.model.utils.ResponseShopSavvy;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class SaleJPARepositoryAdapter extends AdapterOperations<Sale,SaleDto,String,SaleJPARepository> implements SaleRepository {

    protected SaleJPARepositoryAdapter(SaleJPARepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Sale.SaleBuilder.class).build());
    }

    @Override
    public Mono<ResponseShopSavvyDTO<List<Sale>>> getAllSale(PaginationDTO paginationDTO) {
        return null;
    }

    @Override
    public Mono<ResponseShopSavvy<Sale>> createSale(Sale sale) {
        SaleDto saleDto =  repository.save(SaleMapper.saleASaleDto(sale));
        return Mono.just(new ResponseShopSavvy<>(SaleMapper.saleDtoASale(saleDto)));
    }

    @Override
    public Mono<ResponseShopSavvy<SaleReport>> getSaleReport() {
        Mono<BigDecimal> saleToday = Flux.fromIterable(repository.findAll())
                .filter(ele->ele.getStatus().equals(Boolean.TRUE)&&)
                .
        return null;
    }
}
