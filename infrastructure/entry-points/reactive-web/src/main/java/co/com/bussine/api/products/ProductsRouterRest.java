package co.com.bussine.api.products;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
@Configuration
public class ProductsRouterRest {
    private static final String API_PATH = "api/";
    @Bean
    public RouterFunction<ServerResponse> routerFunctionProducts(ProductsHandler productsHandler){
        return RouterFunctions.route()
                .GET(API_PATH+"products/list", productsHandler::getAllProducts)
                .GET(API_PATH+"products/{id}",productsHandler::getIdProducts)
                .POST(API_PATH+"product" , productsHandler::createProduct)
                .PUT(API_PATH+"product-update/{code}",productsHandler::updateProduct)
                .build();
    }
}

