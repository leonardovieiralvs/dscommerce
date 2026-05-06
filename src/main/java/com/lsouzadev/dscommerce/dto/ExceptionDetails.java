package com.lsouzadev.dscommerce.dto;

import java.time.Instant;

public record ExceptionDetails(Instant timestamp, Integer status, String error, String path) {
}
