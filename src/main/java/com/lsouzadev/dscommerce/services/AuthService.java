package com.lsouzadev.dscommerce.services;

import com.lsouzadev.dscommerce.entities.User;
import com.lsouzadev.dscommerce.exceptions.ForbiddenException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public void validateSelfOrAdmin(Long userId) {
        User me = userService.authenticated();
        if (!me.hasAnyRole("ADMIN") && !me.getId().equals(userId)) {
            throw new ForbiddenException("Access danied");
        }
    }
}
