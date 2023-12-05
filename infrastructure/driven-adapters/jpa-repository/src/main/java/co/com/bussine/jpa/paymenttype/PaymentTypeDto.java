package co.com.bussine.jpa.paymenttype;

import co.com.bussine.jpa.category.CategoryDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "payment_type")
public class PaymentTypeDto extends CategoryDto {


    public PaymentTypeDto() {
    }

    public PaymentTypeDto(String id, String name, String description, Boolean status, String search, LocalDateTime createAt, LocalDateTime updateAt) {
        super(id, name, description, status, search, createAt, updateAt);
    }

    @Override
    public String concatText() {
        return super.concatText();
    }
}
