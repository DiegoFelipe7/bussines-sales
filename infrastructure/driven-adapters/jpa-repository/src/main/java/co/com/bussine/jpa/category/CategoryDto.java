package co.com.bussine.jpa.category;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public  class CategoryDto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private Boolean status;
    private String search;
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @PrePersist
    public void prePersist() {
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.status=true;
    }

    @PreUpdate
    public void preUpdate() {
        this.updateAt = LocalDateTime.now();
    }

    public String concatText(){
        return (this.name+"|"+this.description).toLowerCase();
    }
}
