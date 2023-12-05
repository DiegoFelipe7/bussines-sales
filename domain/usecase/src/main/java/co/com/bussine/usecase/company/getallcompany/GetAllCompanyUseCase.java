package co.com.bussine.usecase.company.getallcompany;

import co.com.bussine.model.company.Company;
import co.com.bussine.model.company.gateways.CompanyRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class GetAllCompanyUseCase implements Supplier<Mono<ResponseShopSavvy<Company>>> {
    private final CompanyRepository companyRepository;
    @Override
    public Mono<ResponseShopSavvy<Company>> get() {
        return companyRepository.getAllCompany();
    }
}
