package com.lsouzadev.dscommerce.repositories;

import com.lsouzadev.dscommerce.entities.OrderItem;
import com.lsouzadev.dscommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
