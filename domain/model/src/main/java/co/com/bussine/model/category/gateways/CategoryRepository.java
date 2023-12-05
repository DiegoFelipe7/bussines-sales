package co.com.bussine.model.category.gateways;
import co.com.bussine.model.category.Category;
import co.com.bussine.model.utils.PaginationDTO;
import co.com.bussine.model.utils.ResponseShopSavvy;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import reactor.core.publisher.Mono;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface CategoryRepository {
    Mono<ResponseShopSavvyDTO<List<Category>>> getAllCategory(PaginationDTO paginationDTO);
    Mono<ResponseShopSavvy<Category>> getIdCategory(String id);
    Mono<ResponseShopSavvy<Category>> createCategory(Category category);
    Mono<ResponseShopSavvy<Category>> updateCategory(String id,Category category);
    Mono<ResponseShopSavvy<Category>> updateStatus(String id);
    Mono<byte[]> generateReport();

    Mono<byte[]> exportToXls() throws FileNotFoundException;

}
