package com.mysite.cuffee.cart.repository;

import com.mysite.cuffee.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
