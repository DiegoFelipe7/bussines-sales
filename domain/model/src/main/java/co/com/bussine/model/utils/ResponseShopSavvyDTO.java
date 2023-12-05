package co.com.bussine.model.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseShopSavvyDTO<R>{
    private R body;
    private PaginationDTO pagination;
}
