package co.com.bussine.api.paymenttype;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PaymentTypeRouterRest {
    private static final String API_PATH = "api/";

    @Bean
    public RouterFunction<ServerResponse> routerFunctionPaymentType(PaymentTypeHandler paymentTypeHandler) {
        return RouterFunctions.route()
                .GET(API_PATH + "payment-type/list", paymentTypeHandler::getAllPaymentType)
                .GET(API_PATH + "payment-type/{id}", paymentTypeHandler::getIdPaymentType)
                .POST(API_PATH + "payment-type", paymentTypeHandler::createPaymentType)
                .PUT(API_PATH + "payment-type-update", paymentTypeHandler::updatePaymentType)
                .build();
    }
}
