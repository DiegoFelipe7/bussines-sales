package co.com.bussine.jpa.income;

import co.com.bussine.jpa.incomedetail.IncomeDetailDto;
import co.com.bussine.jpa.paymenttype.PaymentTypeDto;
import co.com.bussine.jpa.products.ProductsDto;
import co.com.bussine.jpa.supplier.SupplierDto;
import co.com.bussine.jpa.users.UserDto;
import co.com.bussine.model.paymenttype.PaymentType;
import co.com.bussine.model.products.Products;
import co.com.bussine.model.supplier.Supplier;
import co.com.bussine.model.users.Users;
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
@Table(name = "income")
public class IncomeDto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToOne(targetEntity = SupplierDto.class )
    @JoinColumn(name = "id_supplier")
    private SupplierDto supplier;
    @OneToOne(targetEntity = PaymentTypeDto.class)
    @JoinColumn(name = "id_payment_type")
    private PaymentTypeDto paymentType;
    @Column(name = "create_at")
    private LocalDateTime createAt;
    private String series;
    private String voucherNumber;
    private Integer taxes;
    private BigDecimal total;
    @OneToOne(targetEntity = UserDto.class , fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private UserDto users;
    private String search;
    private Boolean status;
    @Column(name = "update_at")
    private LocalDateTime updateAt;
    @OneToMany(targetEntity = IncomeDetailDto.class, fetch = FetchType.EAGER, mappedBy = "income")
    private List<IncomeDetailDto> incomeDetail;

    @PrePersist
    public void prePersist() {
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.status=true;
    }

    public String concatText(){
        return (this.series+"|"+this.voucherNumber+"|"+this.total).toLowerCase();
    }



}
