package co.com.bussine.model.category;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class Category {
    private String id;
    private String name;
    private String description;
    private Boolean status;
    private String search;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
