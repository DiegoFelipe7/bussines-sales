package co.com.bussine.usecase.products.createproduct;

import co.com.bussine.model.products.Products;
import co.com.bussine.model.products.gateways.ProductsRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class CreateProductUseCase implements Function<Products, Mono<ResponseShopSavvy<Products>>> {
    private final ProductsRepository productsRepository;
    @Override
    public Mono<ResponseShopSavvy<Products>> apply(Products products) {
        return productsRepository.createProduct(products);
    }
}
