package co.com.bussine.jpa.company;

import jakarta.persistence.*;
import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company")
public class CompanyDto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
