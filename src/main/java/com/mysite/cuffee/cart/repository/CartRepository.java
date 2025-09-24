package com.mysite.cuffee.cart.repository;

import com.mysite.cuffee.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartRepository extends JpaRepository<Cart,Integer> {
}
