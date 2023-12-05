package co.com.bussine.usecase.paymenttype.getallpaymenttype;

import co.com.bussine.model.paymenttype.PaymentType;
import co.com.bussine.model.paymenttype.gateways.PaymentTypeRepository;
import co.com.bussine.model.utils.PaginationDTO;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class GetAllPaymentTypeUseCase implements Function<PaginationDTO, Mono<ResponseShopSavvyDTO<List<PaymentType>>>> {
    private final PaymentTypeRepository paymentTypeRepository;
    @Override
    public Mono<ResponseShopSavvyDTO<List<PaymentType>>> apply(PaginationDTO paginationDTO) {
        return paymentTypeRepository.getAllPaymentType(paginationDTO);
    }
}
