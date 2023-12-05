package co.com.bussine.usecase.supplier.updatestatussupplier;

import co.com.bussine.model.supplier.Supplier;
import co.com.bussine.model.supplier.gateways.SupplierRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class UpdateStatusSupplierUseCase implements Function<String, Mono<ResponseShopSavvy<Supplier>>> {
    private final SupplierRepository supplierRepository;
    @Override
    public Mono<ResponseShopSavvy<Supplier>> apply(String id) {
        return supplierRepository.updateStatusSuppler(id);
    }
}
