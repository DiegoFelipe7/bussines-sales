package co.com.bussine.usecase.supplier.getallsupplier;

import co.com.bussine.model.supplier.Supplier;
import co.com.bussine.model.supplier.gateways.SupplierRepository;
import co.com.bussine.model.utils.PaginationDTO;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class GetAllSupplierUseCase implements Function<PaginationDTO, Mono<ResponseShopSavvyDTO<List<Supplier>>>> {
    private final SupplierRepository supplierRepository;
    @Override
    public Mono<ResponseShopSavvyDTO<List<Supplier>>> apply(PaginationDTO paginationDTO) {
        return supplierRepository.getAllSupplier(paginationDTO);
    }
}
