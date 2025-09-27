package com.mysite.cuffee.cart.repository;

import com.mysite.cuffee.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByOrderDateBetween(LocalDateTime batchStartTime, LocalDateTime batchEndTime);
}
