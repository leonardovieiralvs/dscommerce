package com.lsouzadev.dscommerce.exceptions.handlers;

import com.lsouzadev.dscommerce.dto.ExceptionDetails;
import com.lsouzadev.dscommerce.exceptions.DatabaseViolationException;
import com.lsouzadev.dscommerce.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDetails> resourceNotFoundExceptionHandler(ResourceNotFoundException e, HttpServletRequest request) {
        ExceptionDetails error = new ExceptionDetails(Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DatabaseViolationException.class)
    public ResponseEntity<ExceptionDetails> databaseViolationExceptionHandler(DatabaseViolationException e, HttpServletRequest request) {
        ExceptionDetails error = new ExceptionDetails(Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.badRequest().body(error);
    }
}
