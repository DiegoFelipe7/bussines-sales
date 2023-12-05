package co.com.bussine.usecase.category.updatecategory;

import co.com.bussine.model.category.Category;
import co.com.bussine.model.category.gateways.CategoryRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateCategoryUseCase implements BiFunction<String , Category , Mono<ResponseShopSavvy<Category>>> {
    private final CategoryRepository categoryRepository;
    @Override
    public Mono<ResponseShopSavvy<Category>> apply(String id, Category category) {
        return categoryRepository.updateCategory(id,category);

    }
}
