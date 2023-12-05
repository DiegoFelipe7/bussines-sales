package co.com.bussine.api.income;

import co.com.bussine.api.utils.Pagination;
import co.com.bussine.model.income.Income;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import co.com.bussine.usecase.income.createincome.CreateIncomeUseCase;
import co.com.bussine.usecase.income.getallincome.GetAllIncomeUseCase;
import co.com.bussine.usecase.income.getidincome.GetIdIncomeUseCase;
import co.com.bussine.usecase.income.invalidateincome.InvalidateIncomeUseCase;
import co.com.bussine.usecase.income.updateincome.UpdateIncomeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class IncomeHandler {
    private final GetAllIncomeUseCase getAllIncomeUseCase;
    private final GetIdIncomeUseCase getIdIncomeUseCase;
    private final CreateIncomeUseCase createIncomeUseCase;
    private final InvalidateIncomeUseCase invalidateIncomeUseCase;
    private final UpdateIncomeUseCase updateIncomeUseCase;


    public Mono<ServerResponse> getAllIncome(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAllIncomeUseCase.apply(Pagination.pagination(serverRequest)), ResponseShopSavvyDTO.class);
    }

    public Mono<ServerResponse> getIdIncome(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getIdIncomeUseCase.apply(id), ResponseShopSavvyDTO.class);
    }



    public Mono<ServerResponse> createIncome(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Income.class)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createIncomeUseCase.apply(ele), ResponseShopSavvyDTO.class));
    }

    public Mono<ServerResponse> invalidateIncome(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(invalidateIncomeUseCase.apply(id), ResponseShopSavvyDTO.class);
    }

    public Mono<ServerResponse> updateIncome(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(Income.class)
                .flatMap(ele ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(updateIncomeUseCase.apply(id, ele), ResponseShopSavvyDTO.class));
    }


}
