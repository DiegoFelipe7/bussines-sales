package co.com.bussine.model.paymenttype.gateways;

import co.com.bussine.model.paymenttype.PaymentType;
import co.com.bussine.model.utils.PaginationDTO;
import co.com.bussine.model.utils.ResponseShopSavvy;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PaymentTypeRepository {

    Mono<ResponseShopSavvyDTO<List<PaymentType>>> getAllPaymentType(PaginationDTO paginationDTO);
    Mono<ResponseShopSavvy<PaymentType>> getIdPaymentType(String id);
    Mono<ResponseShopSavvy<PaymentType>> createPaymentType(PaymentType paymentType);
    Mono<ResponseShopSavvy<PaymentType>> updatePaymentType(String id , PaymentType paymentType);
    Mono<ResponseShopSavvy<PaymentType>> updateStatePaymentType(String id);
 }
