package co.com.bussine.usecase.category.createcategory;

import co.com.bussine.model.category.Category;
import co.com.bussine.model.category.gateways.CategoryRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class CreateCategoryUseCase implements Function<Category , Mono<ResponseShopSavvy<Category>>> {
    private final CategoryRepository categoryRepository;
    @Override
    public Mono<ResponseShopSavvy<Category>> apply(Category category) {
        return categoryRepository.createCategory(category);
    }
}
