package com.lsouzadev.dscommerce.mapper;

import com.lsouzadev.dscommerce.dto.ProductDto;
import com.lsouzadev.dscommerce.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductDto productDto);

    ProductDto toDto(Product product);

    void updateEntity(@MappingTarget Product product, ProductDto productDto);
}
