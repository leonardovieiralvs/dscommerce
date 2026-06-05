package com.lsouzadev.dscommerce.services;

import com.lsouzadev.dscommerce.dto.OrderDto;
import com.lsouzadev.dscommerce.dto.OrderItemDto;
import com.lsouzadev.dscommerce.entities.Order;
import com.lsouzadev.dscommerce.entities.OrderItem;
import com.lsouzadev.dscommerce.entities.Product;
import com.lsouzadev.dscommerce.entities.User;
import com.lsouzadev.dscommerce.enums.OrderStatus;
import com.lsouzadev.dscommerce.exceptions.ResourceNotFoundException;
import com.lsouzadev.dscommerce.repositories.OrderItemRepository;
import com.lsouzadev.dscommerce.repositories.OrderRepository;
import com.lsouzadev.dscommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Transactional
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserService userService;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.userService = userService;
    }

    public OrderDto findByid(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found."));

        return new OrderDto(order);
    }


    public OrderDto insert(OrderDto dto) {

        Order order = new Order();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = userService.authenticated();
        order.setClient(user);

        for (OrderItemDto itemDto : dto.getItems()) {
            Product product = productRepository.getReferenceById(itemDto.getProductId());
            OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
            order.getItems().add(item);
        }
        orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDto(order);
    }
}
