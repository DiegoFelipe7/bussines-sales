package co.com.bussine.api.paymenttype;

import co.com.bussine.api.utils.Pagination;
import co.com.bussine.model.paymenttype.PaymentType;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import co.com.bussine.usecase.paymenttype.createpaymenttype.CreatePaymentTypeUseCase;
import co.com.bussine.usecase.paymenttype.getallpaymenttype.GetAllPaymentTypeUseCase;
import co.com.bussine.usecase.paymenttype.getidpaymenttype.GetIdPaymentTypeUseCase;
import co.com.bussine.usecase.paymenttype.updatepaymenttype.UpdatePaymentTypeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PaymentTypeHandler {
    private final GetAllPaymentTypeUseCase getAllPaymentTypeUseCase;
    private final GetIdPaymentTypeUseCase getIdPaymentTypeUseCase;
    private final CreatePaymentTypeUseCase createPaymentTypeUseCase;
    private final UpdatePaymentTypeUseCase updatePaymentTypeUseCase;

    public Mono<ServerResponse> getAllPaymentType(ServerRequest serverRequest) {
       return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAllPaymentTypeUseCase.apply(Pagination.pagination(serverRequest)), ResponseShopSavvyDTO.class);
    }
    public Mono<ServerResponse> getIdPaymentType(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getIdPaymentTypeUseCase.apply(id), ResponseShopSavvyDTO.class);
    }

    public Mono<ServerResponse> createPaymentType(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(PaymentType.class)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createPaymentTypeUseCase.apply(ele), ResponseShopSavvyDTO.class));
    }

    public Mono<ServerResponse> updatePaymentType(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(PaymentType.class)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(updatePaymentTypeUseCase.apply(id,ele), ResponseShopSavvyDTO.class));
    }

}
