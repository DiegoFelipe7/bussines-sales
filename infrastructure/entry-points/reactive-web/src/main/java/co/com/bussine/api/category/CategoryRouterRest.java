package co.com.bussine.api.category;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class CategoryRouterRest {
    private static final String API_PATH = "api/";

    @Bean
    public RouterFunction<ServerResponse> routerFunctionCategory(CategoryHandler categoryHandler) {
        return RouterFunctions.route()
                .GET(API_PATH+"category/report",categoryHandler::report)
                .GET(API_PATH+"category",categoryHandler::getAllCategory)
                .GET(API_PATH + "category/{id}", categoryHandler::getIdCategory)
                .POST(API_PATH+"category",categoryHandler::createCategory)
                .PUT(API_PATH+"category-update/{id}",categoryHandler::updateCategory)
                .PATCH(API_PATH+"category-update/status/{id}",categoryHandler::updateCategoryStatus)
                .build();

    }
}
