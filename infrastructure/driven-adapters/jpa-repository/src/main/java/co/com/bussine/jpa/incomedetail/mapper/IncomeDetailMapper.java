package co.com.bussine.jpa.incomedetail.mapper;

import co.com.bussine.jpa.income.IncomeDto;
import co.com.bussine.jpa.income.mapper.IncomeMapper;
import co.com.bussine.jpa.incomedetail.IncomeDetailDto;
import co.com.bussine.jpa.products.ProductsDto;
import co.com.bussine.jpa.products.mapper.ProductsMapper;
import co.com.bussine.model.incomedetail.IncomeDetail;
import co.com.bussine.model.products.Products;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class IncomeDetailMapper {
    private IncomeDetailMapper() {
        throw new IllegalStateException("Utility class");
    }


    public static IncomeDetail incomeDetailDtoAIncomeDetail(IncomeDetailDto incomeDetailDto){
        return IncomeDetail.builder()
                .id(incomeDetailDto.getId())
                .products(ProductsMapper.productsDtoAProduct(incomeDetailDto.getProducts()))
                .priceBuy(incomeDetailDto.getPriceBuy())
                .priceSale(incomeDetailDto.getPriceSale())
                .amount(incomeDetailDto.getAmount())
                .status(incomeDetailDto.getStatus())
                .createAt(incomeDetailDto.getCreateAt())
                .updateAt(incomeDetailDto.getUpdateAt())
                .build();
    }

    public static IncomeDetailDto incomeDetailDtoCreate(IncomeDto incomeDto, ProductsDto productsDto, Integer quantity,BigDecimal priceBuy,BigDecimal priceSale){
        return IncomeDetailDto.builder()
                .income(incomeDto)
                .products(productsDto)
                .amount(quantity)
                .priceBuy(priceBuy)
                .priceSale(priceSale)
                .build();
    }

    public static IncomeDetailDto incomeDetailDtoUpdate(IncomeDetailDto incomeDetailDto, ProductsDto productsDto, Integer quantity,BigDecimal priceBuy,BigDecimal priceSale){
        return IncomeDetailDto.builder()
                .id(incomeDetailDto.getId())
                .income(incomeDetailDto.getIncome())
                .products(productsDto)
                .amount(quantity)
                .priceBuy(priceBuy)
                .priceSale(priceSale)
                .status(incomeDetailDto.getStatus())
                .createAt(incomeDetailDto.getCreateAt())
                .updateAt(LocalDateTime.now())
                .build();
    }
}
