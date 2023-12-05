package co.com.bussine.model.company.gateways;

import co.com.bussine.model.company.Company;
import co.com.bussine.model.company.Report;
import co.com.bussine.model.utils.ResponseShopSavvy;
import reactor.core.publisher.Mono;

public interface CompanyRepository {
    Mono<ResponseShopSavvy<Company>> getAllCompany();
    Mono<ResponseShopSavvy<Company>> saveCompany(Company company);
    Mono<ResponseShopSavvy<Company>> updateCompany(String id, Company company);
    Mono<ResponseShopSavvy<Report>> getAllReport();
 }
