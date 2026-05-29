package com.lsouzadev.dscommerce.controllers;

import com.lsouzadev.dscommerce.config.UserService;
import com.lsouzadev.dscommerce.dto.OrderDto;
import com.lsouzadev.dscommerce.dto.UserDto;
import com.lsouzadev.dscommerce.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findByid(id));
    }
}
