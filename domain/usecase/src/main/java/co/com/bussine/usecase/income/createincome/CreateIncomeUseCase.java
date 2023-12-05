package co.com.bussine.usecase.income.createincome;

import co.com.bussine.model.income.Income;
import co.com.bussine.model.income.gateways.IncomeRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class CreateIncomeUseCase implements Function<Income, Mono<ResponseShopSavvy<Income>>> {
    private final IncomeRepository incomeRepository;
    @Override
    public Mono<ResponseShopSavvy<Income>> apply(Income income) {
        return incomeRepository.createIncome(income);
    }
}
