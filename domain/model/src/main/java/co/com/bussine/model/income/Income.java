package co.com.bussine.model.income;

import co.com.bussine.model.paymenttype.PaymentType;
import co.com.bussine.model.products.Products;
import co.com.bussine.model.supplier.Supplier;
import co.com.bussine.model.users.Users;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Income {
    private String id;
    private Supplier supplier;
    private PaymentType paymentType;
    private LocalDateTime createAt;
    private String series;
    private String voucherNumber;
    private List<Products> products;
    private Integer taxes;
    private BigDecimal total;
    private Users users;
    private String search;
    private Boolean status;
    private LocalDateTime updateAt;

    public BigDecimal totalAmount() {
        return this.products.stream()
                .map(ele -> ele.getPriceBuy().multiply(BigDecimal.valueOf(ele.getStock())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }



}
