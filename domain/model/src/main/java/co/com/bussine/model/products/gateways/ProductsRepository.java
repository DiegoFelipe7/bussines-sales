package co.com.bussine.model.products.gateways;

import co.com.bussine.model.products.Products;
import co.com.bussine.model.utils.PaginationDTO;
import co.com.bussine.model.utils.ResponseShopSavvy;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductsRepository {
    Mono<ResponseShopSavvyDTO<List<Products>>> getAllProducts(PaginationDTO paginationDTO);
    Mono<ResponseShopSavvy<Products>> getIdProduct(String id);
    Mono<ResponseShopSavvy<Products>> createProduct(Products products);
    Mono<ResponseShopSavvy<Products>> updateProduct(String id, Products products);
    Mono<Products> updateProductProperties(Products products, Boolean isEdit);
    Mono<Long> countProducts();



}
