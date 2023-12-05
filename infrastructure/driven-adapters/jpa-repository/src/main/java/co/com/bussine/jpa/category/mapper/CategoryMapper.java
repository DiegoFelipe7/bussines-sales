package co.com.bussine.jpa.category.mapper;

import co.com.bussine.jpa.category.CategoryDto;
import co.com.bussine.model.category.Category;

public class CategoryMapper {

    private CategoryMapper() {
        throw new IllegalStateException("Utility class");
    }
    public static Category categoryDtoACategory(CategoryDto categoryDto){
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .status(categoryDto.getStatus())
                .search(categoryDto.concatText())
                .createAt(categoryDto.getCreateAt())
                .updateAt(categoryDto.getUpdateAt())
                .build();
    }

    public static CategoryDto categoryACategoryDto(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .status(category.getStatus())
                .search(category.getSearch())
                .createAt(category.getCreateAt())
                .updateAt(category.getUpdateAt())
                .build();
    }
}
