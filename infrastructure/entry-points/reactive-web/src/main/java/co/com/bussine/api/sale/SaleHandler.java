package co.com.bussine.api.sale;

import co.com.bussine.model.sale.Sale;
import co.com.bussine.model.utils.ResponseShopSavvy;
import co.com.bussine.usecase.sale.createsale.CreateSaleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SaleHandler {
    private final CreateSaleUseCase createSaleUseCase;

    public Mono<ServerResponse> createSale(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Sale.class)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createSaleUseCase.apply(ele), ResponseShopSavvy.class));
    }
}
