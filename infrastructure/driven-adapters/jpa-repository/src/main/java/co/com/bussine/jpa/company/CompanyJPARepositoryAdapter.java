package co.com.bussine.jpa.company;

import co.com.bussine.jpa.company.mapper.CompanyMapper;
import co.com.bussine.jpa.helper.AdapterOperations;
import co.com.bussine.model.clients.gateways.ClientsRepository;
import co.com.bussine.model.common.BusinessException;
import co.com.bussine.model.company.Company;
import co.com.bussine.model.company.Report;
import co.com.bussine.model.company.gateways.CompanyRepository;
import co.com.bussine.model.income.gateways.IncomeRepository;
import co.com.bussine.model.incomedetail.IncomeDetail;
import co.com.bussine.model.incomedetail.gateways.IncomeDetailRepository;
import co.com.bussine.model.products.gateways.ProductsRepository;
import co.com.bussine.model.supplier.gateways.SupplierRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Repository
public class CompanyJPARepositoryAdapter extends AdapterOperations<Company, CompanyDto, String, CompanyJPARepository> implements CompanyRepository {
    private final ProductsRepository productsRepository;
    private final SupplierRepository supplierRepository;
    private final IncomeRepository incomeRepository;
    private final ClientsRepository clientsRepository;

    public CompanyJPARepositoryAdapter(CompanyJPARepository repository,
                                       ObjectMapper mapper,
                                       ProductsRepository productsRepository,
                                       IncomeRepository incomeRepository,
                                       ClientsRepository clientsRepository,
                                       SupplierRepository supplierRepository
    ) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Company.CompanyBuilder.class).build());
        this.productsRepository = productsRepository;
        this.incomeRepository = incomeRepository;
        this.clientsRepository = clientsRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Mono<ResponseShopSavvy<Company>> getAllCompany() {
        return Flux.fromIterable(repository.findAll())
                .next()
                .map(ele -> new ResponseShopSavvy<>(CompanyMapper.companyDtoACompany(ele)));
    }

    @Override
    public Mono<ResponseShopSavvy<Company>> saveCompany(Company company) {
        return null;
    }

    @Override
    public Mono<ResponseShopSavvy<Company>> updateCompany(String id, Company company) {
        return repository.findById(id)
                .map(ele -> {
                    ele.setId(id);
                    CompanyDto companyDto = repository.save(CompanyMapper.companyACompanyDto(company));
                    return new ResponseShopSavvy<>(CompanyMapper.companyDtoACompany(companyDto));
                }).map(Mono::just)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.NO_EXISTE_ESTA_EMPRESA));
    }

    @Override
    public Mono<ResponseShopSavvy<Report>> getAllReport() {
        Mono<BigDecimal> totalIncome = incomeRepository.totalIncome();
        Mono<Long> clients = clientsRepository.countClients();
        Mono<Long> suppler = supplierRepository.countSupplier();
        Mono<Long> products = productsRepository.countProducts();
        return Mono.zip(totalIncome, clients, suppler, products).map(ele -> {
            Report report = new Report(ele.getT1(), BigDecimal.ZERO, ele.getT2(), ele.getT3(), ele.getT4());
            return new ResponseShopSavvy<>(report);
        });
    }
}
