package com.lsouzadev.dscommerce.dto;

import com.lsouzadev.dscommerce.entities.Category;
import com.lsouzadev.dscommerce.entities.Product;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

public class ProductDto {
    private Long id;

    @Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String name;

    private String description;

    @NotNull(message = "Campo requirido")
    @Positive(message = "O preço deve ser positivo")
    private Double price;

    private String imgUrl;

    @NotEmpty(message = "Deve ter pelo menos uma categoria")
    private List<CategoryDto> categories = new ArrayList<>();

    public ProductDto(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
}

    public ProductDto(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        imgUrl = product.getImgUrl();
        for (Category category : product.getCategories()) {
            categories.add(new CategoryDto(category));
        }
    }

    public ProductDto() {
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres") @NotBlank(message = "Campo requerido") String getName() {
        return name;
    }

    public void setName(@Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres") @NotBlank(message = "Campo requerido") String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @NotNull(message = "Campo requirido") @Positive(message = "O preço deve ser positivo") Double getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "Campo requirido") @Positive(message = "O preço deve ser positivo") Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

