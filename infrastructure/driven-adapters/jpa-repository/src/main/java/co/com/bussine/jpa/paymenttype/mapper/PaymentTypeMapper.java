package co.com.bussine.jpa.paymenttype.mapper;

import co.com.bussine.jpa.paymenttype.PaymentTypeDto;
import co.com.bussine.model.paymenttype.PaymentType;

public class PaymentTypeMapper {
    private PaymentTypeMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static PaymentType paymentTypeDtoAPayment(PaymentTypeDto paymentTypeDto){
        return PaymentType.paymentTypeBuilder()
                .id(paymentTypeDto.getId())
                .name(paymentTypeDto.getName())
                .description(paymentTypeDto.getDescription())
                .status(paymentTypeDto.getStatus())
                .search(paymentTypeDto.concatText())
                .createAt(paymentTypeDto.getCreateAt())
                .updateAt(paymentTypeDto.getUpdateAt())
                .build();
    }

    public static PaymentTypeDto paymentTypeAPaymentDto(PaymentType paymentType){
        return new PaymentTypeDto(paymentType.getId(),
                paymentType.getName(),
                paymentType.getDescription(),
                paymentType.getStatus(),
                paymentType.getSearch(),
                paymentType.getCreateAt(),
                paymentType.getUpdateAt());
    }
}
