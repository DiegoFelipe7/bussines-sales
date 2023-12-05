package co.com.bussine.api.supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
@Configuration
public class SupplierRouterRest {

    private static final String API_PATH = "api/";
    @Bean
    public RouterFunction<ServerResponse> routerFunctionSupplier(SupplierHandler supplierHandler){
        return RouterFunctions.route()
                .GET(API_PATH+"suppliers/list", supplierHandler::getAllSupplier)
                .GET(API_PATH+"suppliers/{id}",supplierHandler::getIdSupplier)
                .POST(API_PATH+"supplier" , supplierHandler::createSupplier)
                .PUT(API_PATH+"supplier-update/{id}",supplierHandler::updateSupplier)
                .PATCH(API_PATH+"supplier-status/{id}",supplierHandler::updateSupplierStatus)
                .build();
    }
}
