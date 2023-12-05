package co.com.bussine.model.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Report {
    private BigDecimal buy;
    private BigDecimal sale;
    private Long clients;
    private Long supplier;
    private Long products;
}
