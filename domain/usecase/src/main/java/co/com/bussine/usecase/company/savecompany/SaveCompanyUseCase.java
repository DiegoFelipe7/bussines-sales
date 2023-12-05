package co.com.bussine.usecase.company.savecompany;

import co.com.bussine.model.company.Company;
import co.com.bussine.model.company.gateways.CompanyRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class SaveCompanyUseCase implements Function<Company, Mono<ResponseShopSavvy<Company>>> {
    private final CompanyRepository companyRepository;
    @Override
    public Mono<ResponseShopSavvy<Company>> apply(Company company) {
        return companyRepository.saveCompany(company);
    }
}
