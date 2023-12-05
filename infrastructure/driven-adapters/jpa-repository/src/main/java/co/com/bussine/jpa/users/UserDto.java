package co.com.bussine.jpa.users;

import co.com.bussine.jpa.clients.ClientsDto;
import co.com.bussine.model.clients.Clients;
import co.com.bussine.model.enums.DocumentType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "users")
public class UserDto extends ClientsDto {
    private String password;
    private String role;




}
