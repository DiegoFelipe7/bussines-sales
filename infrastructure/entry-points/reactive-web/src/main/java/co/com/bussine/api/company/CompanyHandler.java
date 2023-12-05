package co.com.bussine.api.company;

import co.com.bussine.api.utils.Pagination;
import co.com.bussine.model.company.Company;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import co.com.bussine.usecase.company.getallcompany.GetAllCompanyUseCase;
import co.com.bussine.usecase.company.getallreport.GetAllReportUseCase;
import co.com.bussine.usecase.company.updatecompany.UpdateCompanyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CompanyHandler {
    private final GetAllCompanyUseCase getAllCompanyUseCase;
    private final GetAllReportUseCase getAllReportUseCase;
    private final UpdateCompanyUseCase updateCompanyUseCase;

    public Mono<ServerResponse> getAllCompany(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAllCompanyUseCase.get(), ResponseShopSavvyDTO.class);
    }

    public Mono<ServerResponse> getAllReport(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAllReportUseCase.get(), ResponseShopSavvyDTO.class);
    }

    public Mono<ServerResponse> updateCompany(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(Company.class)
                .flatMap(ele ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(updateCompanyUseCase.apply(id, ele), ResponseShopSavvyDTO.class));
    }
}
