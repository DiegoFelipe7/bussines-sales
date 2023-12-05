package co.com.bussine.usecase.income.updateincome;

import co.com.bussine.model.income.Income;
import co.com.bussine.model.income.gateways.IncomeRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateIncomeUseCase implements BiFunction<String,Income, Mono<ResponseShopSavvy<Income>>> {
   private final IncomeRepository incomeRepository;
    @Override
    public Mono<ResponseShopSavvy<Income>> apply(String id,Income income) {
        return incomeRepository.updateIncome(id,income);
    }
}
