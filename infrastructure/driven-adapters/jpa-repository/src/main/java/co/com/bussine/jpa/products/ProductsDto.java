package co.com.bussine.jpa.products;

import co.com.bussine.jpa.category.CategoryDto;
import co.com.bussine.jpa.income.IncomeDto;
import co.com.bussine.jpa.incomedetail.IncomeDetailDto;
import co.com.bussine.model.common.BusinessException;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class ProductsDto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true, nullable = false, length = 4)
    private String code;
    private String name;
    private String description;
    private BigDecimal priceBuy;
    private BigDecimal priceSale;
    private BigDecimal utility;
    private Integer stock;
    private String image;
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = CategoryDto.class)
    @JoinTable(name = "products_category", joinColumns = @JoinColumn(name = "productsId"), inverseJoinColumns = @JoinColumn(name = "categoryId"))
    private List<CategoryDto> category;
    private String search;
    private Boolean status;
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column(name = "update_at")
    private LocalDateTime updateAt;
    @OneToMany(targetEntity =  IncomeDetailDto.class , fetch = FetchType.EAGER , mappedBy = "products")
    private List<IncomeDetailDto> incomeDetail;
    @PrePersist
    public void prePersist() {
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.status=Boolean.TRUE;
        this.stock=0;
        this.priceSale=BigDecimal.ZERO;
        this.priceBuy=BigDecimal.ZERO;
    }
    @PreUpdate
    public void preUpdate() {
        this.updateAt = LocalDateTime.now();
    }


    public BigDecimal utility() {
        BigDecimal total = this.priceSale.subtract(this.priceBuy);
        if (total.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException(BusinessException.Type.ERROR_USUARIO_EXISTE);
        }
        return total;
    }
    public Integer updateStock(Boolean isEdit , Integer stock){
            if(Boolean.TRUE.equals(isEdit)){
               return this.getStock()-stock;
            }
            return this.stock+stock;
    }




}
