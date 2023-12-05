package co.com.bussine.usecase.company.getallreport;

import co.com.bussine.model.company.Report;
import co.com.bussine.model.company.gateways.CompanyRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class GetAllReportUseCase implements Supplier<Mono<ResponseShopSavvy<Report>>> {
    private final CompanyRepository companyRepository;
    @Override
    public Mono<ResponseShopSavvy<Report>> get() {
        return companyRepository.getAllReport();
    }
}
