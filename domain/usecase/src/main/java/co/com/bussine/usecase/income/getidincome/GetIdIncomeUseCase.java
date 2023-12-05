package co.com.bussine.usecase.income.getidincome;

import co.com.bussine.model.income.Incomes;
import co.com.bussine.model.income.gateways.IncomeRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetIdIncomeUseCase implements Function<String, Mono<ResponseShopSavvy<Incomes>>> {
    private final IncomeRepository incomeRepository;
    @Override
    public Mono<ResponseShopSavvy<Incomes>> apply(String id) {
        return incomeRepository.getIdIncome(id);
    }
}
