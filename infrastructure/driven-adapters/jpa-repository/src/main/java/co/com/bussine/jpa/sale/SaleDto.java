package co.com.bussine.jpa.sale;

import co.com.bussine.jpa.clients.ClientsDto;
import co.com.bussine.jpa.paymenttype.PaymentTypeDto;
import co.com.bussine.jpa.users.UserDto;
import co.com.bussine.model.clients.Clients;
import co.com.bussine.model.paymenttype.PaymentType;
import co.com.bussine.model.products.Products;
import co.com.bussine.model.users.Users;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sale")
public class SaleDto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToOne(targetEntity = ClientsDto.class)
    private ClientsDto clients;
    @OneToOne(targetEntity = UserDto.class)
    private UserDto users;
    @OneToOne(targetEntity = PaymentTypeDto.class)
    private PaymentTypeDto paymentType;
    private String voucherNumber;
    private BigDecimal total;
    private BigDecimal tax;
    private Boolean status;
    private String search;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @PrePersist
    public void prePersist() {
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.status=Boolean.TRUE;
        this.voucherNumber= UUID.randomUUID().toString();

    }
    @PreUpdate
    public void preUpdate() {
        this.updateAt = LocalDateTime.now();
    }

}
