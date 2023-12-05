package co.com.bussine.jpa.sale.mapper;

import co.com.bussine.jpa.clients.mapper.ClientsMapper;
import co.com.bussine.jpa.paymenttype.mapper.PaymentTypeMapper;
import co.com.bussine.jpa.sale.SaleDto;
import co.com.bussine.model.sale.Sale;

public class SaleMapper {
    private SaleMapper() {
        throw new IllegalStateException("Utility class");
    }
    public static Sale saleDtoASale(SaleDto saleDto){
        return Sale.builder()
                .id(saleDto.getId())
                .clients(ClientsMapper.clientsDtoAClients(saleDto.getClients()))
                //.users(productsDto.getName())
                .paymentType(PaymentTypeMapper.paymentTypeDtoAPayment(saleDto.getPaymentType()))
                .voucherNumber(saleDto.getVoucherNumber())
                .total(saleDto.getTotal())
                .tax(saleDto.getTax())
                .search(saleDto.getSearch())
                .status(saleDto.getStatus())
                .createAt(saleDto.getCreateAt())
                .updateAt(saleDto.getUpdateAt())
                .build();
    }
    public static SaleDto saleASaleDto(Sale sale){
        return SaleDto.builder()
                .id(sale.getId())
                .clients(ClientsMapper.clientsAClientsDto(sale.getClients()))
                //.users(productsDto.getName())
                .paymentType(PaymentTypeMapper.paymentTypeAPaymentDto(sale.getPaymentType()))
                .voucherNumber(sale.getVoucherNumber())
                .total(sale.getTotal())
                .tax(sale.getTax())
                .search(sale.getSearch())
                .status(sale.getStatus())
                .createAt(sale.getCreateAt())
                .updateAt(sale.getUpdateAt())
                .build();
    }
}
