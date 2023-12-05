package co.com.bussine.api.supplier;

import co.com.bussine.api.utils.Pagination;
import co.com.bussine.model.products.Products;
import co.com.bussine.model.supplier.Supplier;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import co.com.bussine.usecase.products.updateproduct.UpdateProductUseCase;
import co.com.bussine.usecase.supplier.createsupplier.CreateSupplierUseCase;
import co.com.bussine.usecase.supplier.getallsupplier.GetAllSupplierUseCase;
import co.com.bussine.usecase.supplier.getidsupplier.GetIdSupplierUseCase;
import co.com.bussine.usecase.supplier.updatestatussupplier.UpdateStatusSupplierUseCase;
import co.com.bussine.usecase.supplier.updatesupplier.UpdateSupplierUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SupplierHandler {
    private final GetAllSupplierUseCase getAllSupplierUseCase;
    private final GetIdSupplierUseCase getIdSupplierUseCase;
    private final CreateSupplierUseCase createSupplierUseCase;
    private final UpdateSupplierUseCase updateSupplierUseCase;
    private final UpdateStatusSupplierUseCase updateStatusSupplierUseCase;
    public Mono<ServerResponse> getAllSupplier(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAllSupplierUseCase.apply(Pagination.pagination(serverRequest)), ResponseShopSavvyDTO.class);
    }


    public Mono<ServerResponse> getIdSupplier(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getIdSupplierUseCase.apply(id), ResponseShopSavvyDTO.class);
    }

    public Mono<ServerResponse> createSupplier(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Supplier.class)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createSupplierUseCase.apply(ele), ResponseShopSavvyDTO.class));
    }

    public Mono<ServerResponse> updateSupplier(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(Supplier.class)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(updateSupplierUseCase.apply(id,ele), ResponseShopSavvyDTO.class));
    }

    public Mono<ServerResponse> updateSupplierStatus(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return  ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(updateStatusSupplierUseCase.apply(id), ResponseShopSavvyDTO.class);
    }

}
