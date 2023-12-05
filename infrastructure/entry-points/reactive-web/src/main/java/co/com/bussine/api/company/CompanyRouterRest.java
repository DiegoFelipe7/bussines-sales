package co.com.bussine.api.company;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
@Configuration
public class CompanyRouterRest {
    private static final String API_PATH = "api/";
    @Bean
    public RouterFunction<ServerResponse> routerFunctionCompany(CompanyHandler companyHandler){
        return RouterFunctions.route()
                .GET(API_PATH+"company/list", companyHandler::getAllCompany)
                .GET(API_PATH+"company/report",companyHandler::getAllReport)
                .PUT(API_PATH+"company-update/{id}" , companyHandler::updateCompany)
                .build();
    }
}
