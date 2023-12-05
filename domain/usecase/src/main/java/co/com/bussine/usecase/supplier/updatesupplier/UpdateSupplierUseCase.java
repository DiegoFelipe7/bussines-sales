package co.com.bussine.usecase.supplier.updatesupplier;

import co.com.bussine.model.supplier.Supplier;
import co.com.bussine.model.supplier.gateways.SupplierRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateSupplierUseCase implements BiFunction<String, Supplier, Mono<ResponseShopSavvy<Supplier>>> {
    private final SupplierRepository supplierRepository;

    @Override
    public Mono<ResponseShopSavvy<Supplier>> apply(String id, Supplier supplier) {
        return supplierRepository.updateSuppler(id,supplier);
    }
}
