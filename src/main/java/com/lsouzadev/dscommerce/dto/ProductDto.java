package com.lsouzadev.dscommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ProductDto(Long id,
                         @Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres")
                         @NotBlank(message = "Campo requerido")
                         String name,

                         @Size(min = 10, message = "Descrição precisa ter no mínimo 10 caracteres")
                         @NotBlank(message = "Campo requerido")
                         String description,

                         @Positive(message = "O preço deve ser positivo")
                         Double price,

                         String imgUrl,

                         @NotEmpty(message = "Deve ter pelo menos uma categoria")
                         List<CategoryDto> categories) {

}
