package com.mysite.cuffee.order.service;

import com.mysite.cuffee.order.dto.OrderDto;
import com.mysite.cuffee.order.entity.Customer;
import com.mysite.cuffee.order.entity.OrderItem;
import com.mysite.cuffee.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderItem create(OrderDto orderDto) {
        OrderItem orderItem = new OrderItem();

//        orderItem.setCart(orderDto.getCart());
        orderItem.setCoffee(orderDto.getCoffee());
        orderItem.setQuantity(orderDto.getQuantity());
        orderItem.setSubtotalPrice(orderDto.getSubtotalPrice());
        orderItem.setCreateDate(LocalDateTime.now());

        return orderRepository.save(orderItem);
    }


}
