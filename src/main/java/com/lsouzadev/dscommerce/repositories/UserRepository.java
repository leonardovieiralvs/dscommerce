package com.lsouzadev.dscommerce.repositories;

import com.lsouzadev.dscommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<com.lsouzadev.dscommerce.entities.User, Long> {

    User findByEmail(String email);
}
