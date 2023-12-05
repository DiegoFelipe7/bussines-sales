package co.com.bussine.model.clients;
import co.com.bussine.model.enums.DocumentType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class Clients {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private DocumentType documentType;
    private String identification;
    private String address;
    private String phone;
    private String search;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public String concatText(){
        return this.identification+","+this.name+","+this.lastName+","+this.email+","+this.phone;
    }

}
