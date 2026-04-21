package com.lsouzadev.dscommerce.repository;

import com.lsouzadev.dscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
