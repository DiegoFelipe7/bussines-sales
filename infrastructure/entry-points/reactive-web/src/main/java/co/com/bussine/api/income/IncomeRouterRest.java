package co.com.bussine.api.income;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
@Configuration
public class IncomeRouterRest {
    private static final String API_PATH = "api/";
    @Bean
    public RouterFunction<ServerResponse> routerFunctionIncomes(IncomeHandler incomeHandler){
        return RouterFunctions.route()
                .GET(API_PATH+"incomes/list", incomeHandler::getAllIncome)
                .GET(API_PATH+"income/{id}", incomeHandler::getIdIncome)
                .PATCH(API_PATH+"income-invalid/{id}",incomeHandler::invalidateIncome)
                .POST(API_PATH+"income",incomeHandler::createIncome)
                .POST(API_PATH+"income-update/{id}", incomeHandler::updateIncome)
                .build();
    }
}
