package co.com.bussine.usecase.products.getidproduct;

import co.com.bussine.model.products.Products;
import co.com.bussine.model.products.gateways.ProductsRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetIdProductUseCase implements Function<String , Mono<ResponseShopSavvy<Products>>> {
    private final ProductsRepository productsRepository;
    @Override
    public Mono<ResponseShopSavvy<Products>> apply(String id) {
        return productsRepository.getIdProduct(id);
    }
}
