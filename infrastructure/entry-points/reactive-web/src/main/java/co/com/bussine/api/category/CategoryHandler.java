package co.com.bussine.api.category;


import co.com.bussine.api.utils.Pagination;
import co.com.bussine.model.category.Category;
import co.com.bussine.model.utils.ResponseShopSavvy;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import co.com.bussine.usecase.category.createcategory.CreateCategoryUseCase;
import co.com.bussine.usecase.category.generatereportcategory.GenerateReportCategoryUseCase;
import co.com.bussine.usecase.category.getallcategory.GetAllCategoryUseCase;
import co.com.bussine.usecase.category.getidcategory.GetIdCategoryUseCase;
import co.com.bussine.usecase.category.updatecategory.UpdateCategoryUseCase;
import co.com.bussine.usecase.category.updatestatus.UpdateStatusUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CategoryHandler {
    private final GetAllCategoryUseCase getAllCategoryUseCase;
    private final GetIdCategoryUseCase getIdCategoryUseCase;
    private final CreateCategoryUseCase createCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final UpdateStatusUseCase updateStatusUseCase;
    private final GenerateReportCategoryUseCase generateReportCategoryUseCase;

    public Mono<ServerResponse> report(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(generateReportCategoryUseCase.get(), byte[].class);
    }
    public Mono<ServerResponse> getAllCategory(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAllCategoryUseCase.apply(Pagination.pagination(serverRequest)), ResponseShopSavvyDTO.class);
    }

    public Mono<ServerResponse> getIdCategory(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getIdCategoryUseCase.apply(id), ResponseShopSavvy.class);
    }

    public Mono<ServerResponse> createCategory(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Category.class)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createCategoryUseCase.apply(ele), ResponseShopSavvy.class));

    }

    public Mono<ServerResponse> updateCategory(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(Category.class)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(updateCategoryUseCase.apply(id,ele), ResponseShopSavvy.class));

    }
    public Mono<ServerResponse> updateCategoryStatus(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(Category.class)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(updateStatusUseCase.apply(id), ResponseShopSavvy.class));

    }


}
