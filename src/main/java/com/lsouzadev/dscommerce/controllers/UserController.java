package com.lsouzadev.dscommerce.controllers;

import com.lsouzadev.dscommerce.config.UserService;
import com.lsouzadev.dscommerce.dto.ProductDto;
import com.lsouzadev.dscommerce.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe() {
        return ResponseEntity.ok((userService.getMe()));
    }
}
