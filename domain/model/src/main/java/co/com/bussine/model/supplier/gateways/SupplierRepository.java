package co.com.bussine.model.supplier.gateways;

import co.com.bussine.model.clients.Clients;
import co.com.bussine.model.supplier.Supplier;
import co.com.bussine.model.utils.PaginationDTO;
import co.com.bussine.model.utils.ResponseShopSavvy;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface SupplierRepository {

    Mono<ResponseShopSavvyDTO<List<Supplier>>> getAllSupplier(PaginationDTO paginationDTO);
    Mono<ResponseShopSavvy<Supplier>> getIdSupplier(String identification);

    Mono<ResponseShopSavvy<Supplier>> createSupplier(Supplier supplier);
    Mono<ResponseShopSavvy<Supplier>> updateSuppler(String id,Supplier supplier);

    Mono<ResponseShopSavvy<Supplier>> updateStatusSuppler(String id);
    Mono<Long> countSupplier();
}
