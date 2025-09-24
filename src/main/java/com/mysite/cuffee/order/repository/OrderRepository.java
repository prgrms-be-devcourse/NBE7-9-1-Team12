package com.mysite.cuffee.order.repository;

import com.mysite.cuffee.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderItem, Integer> {
}
