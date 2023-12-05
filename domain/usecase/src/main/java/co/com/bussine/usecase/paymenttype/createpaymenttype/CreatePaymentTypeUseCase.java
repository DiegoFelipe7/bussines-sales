package co.com.bussine.usecase.paymenttype.createpaymenttype;

import co.com.bussine.model.paymenttype.PaymentType;
import co.com.bussine.model.paymenttype.gateways.PaymentTypeRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class CreatePaymentTypeUseCase implements Function<PaymentType, Mono<ResponseShopSavvy<PaymentType>>> {
    private final PaymentTypeRepository paymentTypeRepository;
    @Override
    public Mono<ResponseShopSavvy<PaymentType>> apply(PaymentType paymentType) {
        return paymentTypeRepository.createPaymentType(paymentType);
    }
}
