package com.lsouzadev.dscommerce.repositories;

import com.lsouzadev.dscommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
