package com.lsouzadev.dscommerce.exceptions.handlers;

import com.lsouzadev.dscommerce.dto.FieldMessage;
import com.lsouzadev.dscommerce.dto.CustomError;
import com.lsouzadev.dscommerce.exceptions.DatabaseViolationException;
import com.lsouzadev.dscommerce.exceptions.ForbiddenException;
import com.lsouzadev.dscommerce.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFoundExceptionHandler(ResourceNotFoundException e, HttpServletRequest request) {
        CustomError error = CustomError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DatabaseViolationException.class)
    public ResponseEntity<CustomError> databaseViolationExceptionHandler(DatabaseViolationException e, HttpServletRequest request) {
        CustomError error = CustomError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e, HttpServletRequest request) {

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldMessage> list = fieldErrors.stream()
                .map(fm -> new FieldMessage(fm.getField(), fm.getDefaultMessage()))
                .toList();

        CustomError error = CustomError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .message("Validation error")
                .errors(list)
                .path(request.getRequestURI())

                .build();

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<CustomError> forbiddenExceptionHandler(ForbiddenException e, HttpServletRequest request) {
        CustomError error = CustomError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.FORBIDDEN.value())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(error);
    }


}
