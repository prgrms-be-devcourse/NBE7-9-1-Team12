package com.mysite.cuffee.cart.repository;

import com.mysite.cuffee.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartIdAndProductId(long cartId, long productId);
    Optional<CartItem> findByProductId(long productId);

}
