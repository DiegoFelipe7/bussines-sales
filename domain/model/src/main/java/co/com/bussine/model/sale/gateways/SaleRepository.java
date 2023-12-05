package co.com.bussine.model.sale.gateways;

import co.com.bussine.model.sale.Sale;
import co.com.bussine.model.sale.SaleReport;
import co.com.bussine.model.utils.PaginationDTO;
import co.com.bussine.model.utils.ResponseShopSavvy;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface SaleRepository {
    Mono<ResponseShopSavvyDTO<List<Sale>>> getAllSale(PaginationDTO paginationDTO);
    Mono<ResponseShopSavvy<Sale>> createSale(Sale sale);
    Mono<ResponseShopSavvy<SaleReport>> getSaleReport();
}
