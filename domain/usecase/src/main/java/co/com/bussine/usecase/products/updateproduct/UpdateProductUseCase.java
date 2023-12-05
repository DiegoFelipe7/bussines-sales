package co.com.bussine.usecase.products.updateproduct;

import co.com.bussine.model.products.Products;
import co.com.bussine.model.products.gateways.ProductsRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateProductUseCase implements BiFunction<String, Products, Mono<ResponseShopSavvy<Products>>> {
    private final ProductsRepository productsRepository;
    @Override
    public Mono<ResponseShopSavvy<Products>> apply(String id, Products products) {
        return productsRepository.updateProduct(id,products);
    }
}
