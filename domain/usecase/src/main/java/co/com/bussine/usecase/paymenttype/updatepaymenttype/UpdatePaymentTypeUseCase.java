package co.com.bussine.usecase.paymenttype.updatepaymenttype;

import co.com.bussine.model.paymenttype.PaymentType;
import co.com.bussine.model.paymenttype.gateways.PaymentTypeRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdatePaymentTypeUseCase implements BiFunction<String, PaymentType , Mono<ResponseShopSavvy<PaymentType>>> {
    private final PaymentTypeRepository paymentTypeRepository;
    @Override
    public Mono<ResponseShopSavvy<PaymentType>> apply(String id, PaymentType paymentType) {
        return paymentTypeRepository.updatePaymentType(id,paymentType);
    }
}
