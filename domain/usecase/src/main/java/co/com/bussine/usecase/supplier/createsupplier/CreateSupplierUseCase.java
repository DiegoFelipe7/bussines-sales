package co.com.bussine.usecase.supplier.createsupplier;

import co.com.bussine.model.supplier.Supplier;
import co.com.bussine.model.supplier.gateways.SupplierRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;


@RequiredArgsConstructor
public class CreateSupplierUseCase implements Function<Supplier, Mono<ResponseShopSavvy<Supplier>>> {
    private final SupplierRepository supplierRepository;
    @Override
    public Mono<ResponseShopSavvy<Supplier>> apply(Supplier supplier) {
        return supplierRepository.createSupplier(supplier);
    }
}
