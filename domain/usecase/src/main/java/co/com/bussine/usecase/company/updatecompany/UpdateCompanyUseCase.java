package co.com.bussine.usecase.company.updatecompany;

import co.com.bussine.model.company.Company;
import co.com.bussine.model.company.gateways.CompanyRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateCompanyUseCase implements BiFunction<String, Company, Mono<ResponseShopSavvy<Company>>> {
    private final CompanyRepository companyRepository;
    @Override
    public Mono<ResponseShopSavvy<Company>> apply(String id, Company company) {
        return companyRepository.updateCompany(id,company);
    }
}
