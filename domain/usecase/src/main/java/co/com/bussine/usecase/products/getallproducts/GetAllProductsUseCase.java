package co.com.bussine.usecase.products.getallproducts;

import co.com.bussine.model.products.Products;
import co.com.bussine.model.products.gateways.ProductsRepository;
import co.com.bussine.model.utils.PaginationDTO;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class GetAllProductsUseCase implements Function<PaginationDTO, Mono<ResponseShopSavvyDTO<List<Products>>>> {
    private final ProductsRepository productsRepository;
    @Override
    public Mono<ResponseShopSavvyDTO<List<Products>>> apply(PaginationDTO paginationDTO) {
        return productsRepository.getAllProducts(paginationDTO);
    }
}
