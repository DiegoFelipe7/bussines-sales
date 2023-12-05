package co.com.bussine.model.products;
import co.com.bussine.model.category.Category;
import co.com.bussine.model.common.BusinessException;
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
public class Products {
    private String id;
    private String code;
    private String name;
    private String description;
    private BigDecimal priceBuy;
    private BigDecimal priceSale;
    private BigDecimal utility;
    private Integer stock;
    private String image;
    private List<Category> category;
    private String search;
    private Boolean status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public String concatText(){
        return (this.code+"|"+this.name+"|"+this.description).toLowerCase();
    }


    public BigDecimal utility() {
        BigDecimal total = this.priceSale.subtract(this.priceBuy);
        if (total.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException(BusinessException.Type.ERROR_PRECIO_DE_VENTA_MENOR_A_CERO);
        }
        return total;
    }




}
