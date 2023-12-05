package co.com.bussine.jpa.income;

import co.com.bussine.jpa.helper.AdapterOperations;
import co.com.bussine.jpa.income.mapper.IncomeMapper;
import co.com.bussine.model.common.BusinessException;
import co.com.bussine.model.income.Income;
import co.com.bussine.model.income.Incomes;
import co.com.bussine.model.income.gateways.IncomeRepository;
import co.com.bussine.model.incomedetail.gateways.IncomeDetailRepository;
import co.com.bussine.model.products.gateways.ProductsRepository;
import co.com.bussine.model.utils.PaginationDTO;
import co.com.bussine.model.utils.ResponseShopSavvy;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Repository
public class IncomeJPARepositoryAdapter extends AdapterOperations<Income, IncomeDto, String, IncomeJPARepository> implements IncomeRepository {

    private final IncomeDetailRepository incomeDetailRepository;


    public IncomeJPARepositoryAdapter(IncomeJPARepository repository,
                                      ObjectMapper mapper,
                                      ProductsRepository productsRepository,
                                      IncomeDetailRepository incomeDetailRepository) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Income.IncomeBuilder.class).build());
        this.incomeDetailRepository = incomeDetailRepository;

    }

    @Override
    public Mono<ResponseShopSavvyDTO<List<Income>>> getAllIncome(PaginationDTO paginationDTO) {
        return Flux.fromIterable(repository.findAll())
                .filter(ele -> ele.getStatus().equals(Boolean.TRUE))
                .map(IncomeMapper::incomeDtoAIncome)
                .skip(paginationDTO.getOffset())
                .take(paginationDTO.getLimit())
                .collectList()
                .zipWith(Mono.just(repository.count()))
                .map(ele -> new ResponseShopSavvyDTO<>(ele.getT1(), paginationDTO.pagination(ele.getT2())));

    }

    @Override
    public Mono<ResponseShopSavvy<Incomes>> getIdIncome(String id) {
        return repository.findById(id)
                .map(ele -> new ResponseShopSavvy<>(IncomeMapper.incomeDtoAIncomes(ele)))
                .map(Mono::just)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.NO_EXISTE_ESTE_INGRESO));
    }


    @Override
    public Mono<ResponseShopSavvy<Income>> createIncome(Income income) {
        income.setTotal(income.totalAmount());
        IncomeDto incomeDto = repository.save(IncomeMapper.incomeAIncomeDto(income));
        return Flux.fromIterable(income.getProducts())
                .flatMap(ele -> incomeDetailRepository.createIncomeDetail(IncomeMapper.incomeDtoAIncome(incomeDto), ele, ele.getStock()))
                .then(Mono.just(new ResponseShopSavvy<>(IncomeMapper.incomeDtoAIncome(incomeDto))));
    }

    @Override
    public Mono<ResponseShopSavvy<Income>> cancelIncome(String id) {
        return repository.findById(id)
                .map(ele -> {
                    ele.setStatus(Boolean.FALSE);
                    IncomeDto incomeDto = repository.save(ele);
                    return incomeDetailRepository.invalidateIncomeDetail(ele.getId())
                            .then(Mono.just(new ResponseShopSavvy<>(IncomeMapper.incomeDtoAIncome(incomeDto))));
                })
                .orElseThrow(() -> new BusinessException(BusinessException.Type.NO_EXISTE_ESTE_INGRESO));
    }

    @Override
    public Mono<ResponseShopSavvy<Income>> updateIncome(String id, Income income) {
        return repository.findById(id)
                .map(ele -> {
                    income.setId(ele.getId());
                    income.setStatus(ele.getStatus());
                    income.setSearch(ele.getSearch());
                    income.setTotal(income.totalAmount());
                    IncomeDto incomeDto = repository.save(IncomeMapper.incomeAIncomeDto(income));
                    return Flux.fromIterable(income.getProducts())
                            .flatMap(products -> incomeDetailRepository.updateIncomeDetails(IncomeMapper.incomeDtoAIncome(incomeDto), products, products.getStock()))
                            .then(Mono.just(new ResponseShopSavvy<>(IncomeMapper.incomeDtoAIncome(incomeDto))));
                })
                .orElseThrow(() -> new BusinessException(BusinessException.Type.NO_EXISTE_ESTE_INGRESO));


    }

    @Override
    public Mono<BigDecimal> totalIncome() {
        return Flux.fromIterable(repository.findAll())
                .filter(ele -> ele.getStatus().equals(Boolean.TRUE))
                .filter(data->data.getCreateAt().isAfter(LocalDateTime.now().withDayOfMonth(1)) && data.getCreateAt().isBefore(LocalDateTime.now()))
                .map(IncomeDto::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }


}
