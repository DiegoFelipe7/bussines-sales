package co.com.bussine.api.sale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class SaleRouterRest {
    private static final String API_PATH = "api/";

    @Bean
    public RouterFunction<ServerResponse> routerFunctionSale(SaleHandler saleHandler){
        return RouterFunctions.route()
                .POST(API_PATH+"sale",saleHandler::createSale)
                .build();
    }
}
