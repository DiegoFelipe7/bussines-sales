package co.com.bussine.api.products;

import co.com.bussine.api.utils.Pagination;
import co.com.bussine.model.products.Products;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import co.com.bussine.usecase.products.createproduct.CreateProductUseCase;
import co.com.bussine.usecase.products.getallproducts.GetAllProductsUseCase;
import co.com.bussine.usecase.products.getidproduct.GetIdProductUseCase;
import co.com.bussine.usecase.products.updateproduct.UpdateProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductsHandler {
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final GetIdProductUseCase getIdProductUseCase;
    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;

    public Mono<ServerResponse> getAllProducts(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAllProductsUseCase.apply(Pagination.pagination(serverRequest)), ResponseShopSavvyDTO.class);
    }


    public Mono<ServerResponse> getIdProducts(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getIdProductUseCase.apply(id), ResponseShopSavvyDTO.class);
    }

    public Mono<ServerResponse> createProduct(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Products.class)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createProductUseCase.apply(ele), ResponseShopSavvyDTO.class));
    }

    public Mono<ServerResponse> updateProduct(ServerRequest serverRequest) {
       String code = serverRequest.pathVariable("code");
        return serverRequest.bodyToMono(Products.class)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(updateProductUseCase.apply(code,ele), ResponseShopSavvyDTO.class));
    }


}
