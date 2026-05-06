package com.lsouzadev.dscommerce.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record CustomError(Instant timestamp, Integer status, String message, List<FieldMessage> errors, String path) {
}
