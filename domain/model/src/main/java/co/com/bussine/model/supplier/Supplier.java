package co.com.bussine.model.supplier;
import co.com.bussine.model.enums.DocumentType;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder(toBuilder = true)
public class Supplier {
    private String id;
    private String name;
    private DocumentType documentType;
    private String identification;
    private String email;
    private String address;
    private String phone;

    private Boolean status;
    private String search;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public String concatText(){
        return this.identification+","+this.name+","+this.email+","+this.phone+","+this.phone;
    }
}
