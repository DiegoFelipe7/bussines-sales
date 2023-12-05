package co.com.bussine.usecase.income.getallincome;

import co.com.bussine.model.income.Income;
import co.com.bussine.model.income.gateways.IncomeRepository;
import co.com.bussine.model.utils.PaginationDTO;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class GetAllIncomeUseCase implements Function<PaginationDTO , Mono<ResponseShopSavvyDTO<List<Income>>>> {
   private final IncomeRepository incomeRepository;
    @Override
    public Mono<ResponseShopSavvyDTO<List<Income>>> apply(PaginationDTO paginationDTO) {
        return incomeRepository.getAllIncome(paginationDTO);
    }
}
