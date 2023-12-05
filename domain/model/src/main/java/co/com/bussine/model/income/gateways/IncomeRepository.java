package co.com.bussine.model.income.gateways;

import co.com.bussine.model.income.Income;
import co.com.bussine.model.income.Incomes;
import co.com.bussine.model.utils.PaginationDTO;
import co.com.bussine.model.utils.ResponseShopSavvy;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
public interface IncomeRepository {
    Mono<ResponseShopSavvyDTO<List<Income>>> getAllIncome(PaginationDTO paginationDTO);
    Mono<ResponseShopSavvy<Incomes>> getIdIncome(String id);
    Mono<ResponseShopSavvy<Income>> createIncome(Income income);
    Mono<ResponseShopSavvy<Income>> cancelIncome(String id);
    Mono<ResponseShopSavvy<Income>> updateIncome(String id, Income income);
    Mono<BigDecimal> totalIncome();

}
