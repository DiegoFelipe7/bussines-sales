package co.com.bussine.usecase.income.invalidateincome;

import co.com.bussine.model.income.Income;
import co.com.bussine.model.income.gateways.IncomeRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class InvalidateIncomeUseCase implements Function<String, Mono<ResponseShopSavvy<Income>>> {
    private final IncomeRepository incomeRepository;
    @Override
    public Mono<ResponseShopSavvy<Income>> apply(String id) {
        return incomeRepository.cancelIncome(id);
    }
}
