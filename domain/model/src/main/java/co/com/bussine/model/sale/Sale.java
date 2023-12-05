package co.com.bussine.model.sale;
import co.com.bussine.model.clients.Clients;
import co.com.bussine.model.paymenttype.PaymentType;
import co.com.bussine.model.products.Products;
import co.com.bussine.model.users.Users;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Sale {
    private String id;
    private Clients clients;
    private Users users;
    private PaymentType paymentType;
    private String voucherNumber;
    private List<Products> products;
    private BigDecimal total;
    private BigDecimal tax;
    private Boolean status;
    private String search;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
