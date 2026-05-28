package com.lsouzadev.dscommerce.mapper;

import com.lsouzadev.dscommerce.dto.CategoryDto;
import com.lsouzadev.dscommerce.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryDto categoryDto);

    CategoryDto toDto(Category category);
}
