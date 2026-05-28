package com.lsouzadev.dscommerce.dto;

import java.time.LocalDate;
import java.util.List;

public record UserDto(Long id, String name, String email, String phone, LocalDate birthDate, List<String> roles) {
}
