package co.com.bussine.model.paymenttype;
import co.com.bussine.model.category.Category;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true , builderMethodName = "paymentTypeBuilder")
public class PaymentType  {
    private String id;
    private String name;
    private String description;
    private Boolean status;
    private String search;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    public String concatText(){
        return this.name+","+this.description;
    }

}
