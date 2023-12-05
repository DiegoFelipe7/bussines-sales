package co.com.bussine.jpa.supplier;

import co.com.bussine.jpa.helper.AdapterOperations;
import co.com.bussine.jpa.service.EmailService;
import co.com.bussine.jpa.supplier.mapper.SupplierMapper;
import co.com.bussine.model.common.BusinessException;
import co.com.bussine.model.supplier.Supplier;
import co.com.bussine.model.supplier.gateways.SupplierRepository;
import co.com.bussine.model.utils.PaginationDTO;
import co.com.bussine.model.utils.ResponseShopSavvy;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class SupplierJPARepositoryAdapter extends AdapterOperations<Supplier, SupplierDto, String, SupplierJPARepository>
        implements SupplierRepository {


    public SupplierJPARepositoryAdapter(SupplierJPARepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Supplier.SupplierBuilder.class).build());

    }

    @Override
    public Mono<ResponseShopSavvyDTO<List<Supplier>>> getAllSupplier(PaginationDTO paginationDTO) {
        return Flux.fromIterable(repository.findAllBySearchLike(paginationDTO.getSearch()))
                .filter(ele -> ele.getStatus().equals(Boolean.TRUE))
                .map(SupplierMapper::supplierDtoASupplier)
                .skip(paginationDTO.getOffset())
                .take(paginationDTO.getLimit())
                .collectList()
                .zipWith(Mono.just(repository.count()))
                .map(ele -> new ResponseShopSavvyDTO<>(ele.getT1(), paginationDTO.pagination(ele.getT2())));

    }

    @Override
    public Mono<ResponseShopSavvy<Supplier>> getIdSupplier(String identification) {
        return repository.findByIdentification(identification)
                .map(ele -> new ResponseShopSavvy<>(SupplierMapper.supplierDtoASupplier(ele)))
                .map(Mono::just)
                .orElse(Mono.empty());
    }

    @Override
    public Mono<ResponseShopSavvy<Supplier>> createSupplier(Supplier supplier) {
        if (repository.findByIdentification(supplier.getIdentification()).isPresent()) {
            return Mono.error(new BusinessException(BusinessException.Type.ERROR_VALOR_UNICO));
        }
        SupplierDto supplierDto = repository.save(SupplierMapper.supplierASupplierDto(supplier));
        return Mono.just(new ResponseShopSavvy<>(SupplierMapper.supplierDtoASupplier(supplierDto)));
    }

    @Override
    public Mono<ResponseShopSavvy<Supplier>> updateSuppler(String id, Supplier supplier) {
        return repository.findById(id)
                .map(ele -> {
                    ele.setId(id);
                    SupplierDto supplierDto = repository.save(SupplierMapper.supplierASupplierDto(supplier));
                    return new ResponseShopSavvy<>(SupplierMapper.supplierDtoASupplier(supplierDto));
                })
                .map(Mono::just)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.ERROR_USUARIO_EXISTE));
    }

    @Override
    public Mono<ResponseShopSavvy<Supplier>> updateStatusSuppler(String id) {
        return repository.findById(id)
                .map(ele -> {
                    ele.setId(id);
                    ele.setStatus(!ele.getStatus());
                    SupplierDto supplierDto = repository.save(ele);
                    return new ResponseShopSavvy<>(SupplierMapper.supplierDtoASupplier(supplierDto));
                })
                .map(Mono::just)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.NO_EXISTE_ESTE_PROVEDOR));

    }

    @Override
    public Mono<Long> countSupplier() {
        return Flux.fromIterable(repository.findAll())
                .filter(ele -> ele.getStatus().equals(Boolean.TRUE))
                .count();
    }
}
