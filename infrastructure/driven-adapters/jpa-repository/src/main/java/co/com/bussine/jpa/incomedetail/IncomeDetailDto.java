package co.com.bussine.jpa.incomedetail;

import co.com.bussine.jpa.income.IncomeDto;
import co.com.bussine.jpa.products.ProductsDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "income_detail")
public class IncomeDetailDto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne(targetEntity = IncomeDto.class , fetch = FetchType.LAZY)
    @JoinColumn(name = "income_id" )
    private IncomeDto income;
    @ManyToOne(targetEntity = ProductsDto.class, fetch = FetchType.EAGER )
    private ProductsDto products;
    private Integer amount;
    private BigDecimal priceBuy;
    private BigDecimal priceSale;
    private Boolean status;
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column(name = "update_at")
    private LocalDateTime updateAt;


    @PrePersist
    public void prePersist() {
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.status=true;
    }


    public IncomeDetailDto(String id, ProductsDto products, Integer amount, BigDecimal priceBuy, BigDecimal priceSale, Boolean status, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.products = products;
        this.amount = amount;
        this.priceBuy = priceBuy;
        this.priceSale = priceSale;
        this.status = status;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Integer updateStock(Integer stock){
        if(this.getAmount()>stock){
            return this.getAmount()-stock;
        }
        return this.getAmount()+stock;
    }

}
