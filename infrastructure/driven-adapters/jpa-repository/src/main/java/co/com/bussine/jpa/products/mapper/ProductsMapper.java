package co.com.bussine.jpa.products.mapper;

import co.com.bussine.jpa.category.mapper.CategoryMapper;
import co.com.bussine.jpa.products.ProductsDto;
import co.com.bussine.model.products.Products;
public class ProductsMapper {

    private ProductsMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Products productsDtoAProduct(ProductsDto productsDto){
        return Products.builder()
                .id(productsDto.getId())
                .code(productsDto.getCode())
                .name(productsDto.getName())
                .description(productsDto.getDescription())
                .priceBuy(productsDto.getPriceBuy())
                .priceSale(productsDto.getPriceSale())
                .utility(productsDto.getUtility())
                .stock(productsDto.getStock())
                .image(productsDto.getImage())
                .category(productsDto.getCategory().stream().map(CategoryMapper::categoryDtoACategory).toList())
                .search(productsDto.getSearch())
                .status(productsDto.getStatus())
                .createAt(productsDto.getCreateAt())
                .updateAt(productsDto.getUpdateAt())
                .build();
    }


    public static ProductsDto productsAProductsDto(Products products){
        return ProductsDto.builder()
                .id(products.getId())
                .code(products.getCode())
                .name(products.getName())
                .description(products.getDescription())
                .image(products.getImage())
                .category(products.getCategory().stream().map(CategoryMapper::categoryACategoryDto).toList())
                .search(products.concatText())
                .status(products.getStatus())
                .createAt(products.getCreateAt())
                .updateAt(products.getUpdateAt())
                .build();
    }
}
