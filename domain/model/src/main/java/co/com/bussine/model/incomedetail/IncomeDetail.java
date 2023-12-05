package co.com.bussine.model.incomedetail;
import co.com.bussine.model.income.Income;
import co.com.bussine.model.products.Products;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class IncomeDetail {
    private String id;
    private Income income;
    private Products products;
    private Integer amount;
    private BigDecimal priceBuy;
    private BigDecimal priceSale;
    private Boolean status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;



}
