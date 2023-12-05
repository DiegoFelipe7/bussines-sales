package co.com.bussine.usecase.category.getidcategory;

import co.com.bussine.model.category.Category;
import co.com.bussine.model.category.gateways.CategoryRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetIdCategoryUseCase implements Function<String , Mono<ResponseShopSavvy<Category>>> {
    private final CategoryRepository categoryRepository;
    @Override
    public Mono<ResponseShopSavvy<Category>> apply(String id) {
        return categoryRepository.getIdCategory(id);
    }
}
