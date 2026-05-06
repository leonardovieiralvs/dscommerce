package com.lsouzadev.dscommerce.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

public record ProductDto(Long id,
                         @Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres")
                         @NotBlank(message = "Campo requerido")
                         String name,

                         @Size(min = 10, message = "Descrição precisa ter no mínimo 10 caracteres")
                         @NotBlank(message = "Campo requerido")
                         String description,

                         @Positive(message = "O preço deve ser positivo")
                         Double price,
                         String imgUrl) {

}
