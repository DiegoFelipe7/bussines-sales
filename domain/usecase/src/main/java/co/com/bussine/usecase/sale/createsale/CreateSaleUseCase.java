package co.com.bussine.usecase.sale.createsale;

import co.com.bussine.model.sale.Sale;
import co.com.bussine.model.sale.gateways.SaleRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class CreateSaleUseCase implements Function<Sale , Mono<ResponseShopSavvy<Sale>>> {
    private final SaleRepository saleRepository;
    @Override
    public Mono<ResponseShopSavvy<Sale>> apply(Sale sale) {
        return saleRepository.createSale(sale);
    }
}
