package co.com.bussine.model.company;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Company {
    private String id;
    private String name;
    private String img;
    private String documentType;
    private String identification;
    private String address;
    private String phone;
    private String city;
    private String email;
    private String imposedName;
    private Integer quantity;
    private String money;
    private String symbol;
}
