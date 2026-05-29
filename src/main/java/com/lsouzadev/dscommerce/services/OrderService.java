package com.lsouzadev.dscommerce.services;

import com.lsouzadev.dscommerce.dto.OrderDto;
import com.lsouzadev.dscommerce.entities.Order;
import com.lsouzadev.dscommerce.exceptions.ResourceNotFoundException;
import com.lsouzadev.dscommerce.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto findByid(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found."));

        return new OrderDto(order);
    }
}
