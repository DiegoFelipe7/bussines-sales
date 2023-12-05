package co.com.bussine.jpa.paymenttype;

import co.com.bussine.jpa.helper.AdapterOperations;
import co.com.bussine.jpa.paymenttype.mapper.PaymentTypeMapper;
import co.com.bussine.model.common.BusinessException;
import co.com.bussine.model.paymenttype.PaymentType;
import co.com.bussine.model.paymenttype.gateways.PaymentTypeRepository;
import co.com.bussine.model.utils.PaginationDTO;
import co.com.bussine.model.utils.ResponseShopSavvy;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class PaymentTypeJPARepositoryAdapter extends AdapterOperations<PaymentType, PaymentTypeDto, String, PaymentTypeJPARepository> implements PaymentTypeRepository {

    public PaymentTypeJPARepositoryAdapter(PaymentTypeJPARepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, PaymentType.PaymentTypeBuilder.class).build());
    }

    @Override
    public Mono<ResponseShopSavvyDTO<List<PaymentType>>> getAllPaymentType(PaginationDTO paginationDTO) {
        return Flux.fromIterable(repository.findAllBySearchLike(paginationDTO.getSearch()))
                .filter(ele -> ele.getStatus().equals(Boolean.TRUE))
                .map(PaymentTypeMapper::paymentTypeDtoAPayment)
                .skip(paginationDTO.getOffset())
                .take(paginationDTO.getLimit())
                .collectList()
                .zipWith(Mono.just(repository.count()))
                .map(ele -> new ResponseShopSavvyDTO<>(ele.getT1(), paginationDTO.pagination(ele.getT2())));
    }

    @Override
    public Mono<ResponseShopSavvy<PaymentType>> getIdPaymentType(String id) {
        return repository.findById(id)
                .map(ele -> new ResponseShopSavvy<>(PaymentTypeMapper.paymentTypeDtoAPayment(ele)))
                .map(Mono::just)
                .orElse(Mono.empty());
    }

    @Override
    public Mono<ResponseShopSavvy<PaymentType>> createPaymentType(PaymentType paymentType) {
        PaymentTypeDto paymentTypeDto = repository.save(PaymentTypeMapper.paymentTypeAPaymentDto(paymentType));
        return Mono.just(new ResponseShopSavvy<>(PaymentTypeMapper.paymentTypeDtoAPayment(paymentTypeDto)));
    }

    @Override
    public Mono<ResponseShopSavvy<PaymentType>> updatePaymentType(String id, PaymentType paymentType) {
        return repository.findById(id)
                .map(ele -> {
                    PaymentTypeDto paymentTypeDto = repository.save(PaymentTypeMapper.paymentTypeAPaymentDto(paymentType));
                    return new ResponseShopSavvy<>(PaymentTypeMapper.paymentTypeDtoAPayment(paymentTypeDto));
                })
                .map(Mono::just)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.ERROR_TIPO_DE_PAGO_NO_EXISTE));

    }

    @Override
    public Mono<ResponseShopSavvy<PaymentType>> updateStatePaymentType(String id) {
        return repository.findById(id)
                .map(ele -> {
                    ele.setStatus(!ele.getStatus());
                    PaymentTypeDto paymentTypeDto = repository.save(ele);
                    return new ResponseShopSavvy<>(PaymentTypeMapper.paymentTypeDtoAPayment(paymentTypeDto));
                })
                .map(Mono::just)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.ERROR_TIPO_DE_PAGO_NO_EXISTE));
    }

}
