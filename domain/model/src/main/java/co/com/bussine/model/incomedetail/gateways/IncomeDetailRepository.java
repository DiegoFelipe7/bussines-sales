package co.com.bussine.model.incomedetail.gateways;

import co.com.bussine.model.income.Income;
import co.com.bussine.model.incomedetail.IncomeDetail;
import co.com.bussine.model.products.Products;
import co.com.bussine.model.utils.PaginationDTO;
import co.com.bussine.model.utils.ResponseShopSavvy;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IncomeDetailRepository {
    Mono<ResponseShopSavvyDTO<List<IncomeDetail>>> getAllIncomeDetail(PaginationDTO paginationDTO);

    Mono<List<Products>> getIdIncomeDetails(String id);
    Mono<Void> createIncomeDetail(Income incomeId, Products products, Integer quantity);

    Mono<Void> invalidateIncomeDetail(String id);


    Mono<Void> updateIncomeDetails(Income income, Products products, Integer quantity);

    Mono<ResponseShopSavvy<IncomeDetail>> cancelIncomeDetail(String idIncome, String product);

}
