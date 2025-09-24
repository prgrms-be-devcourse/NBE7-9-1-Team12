package com.mysite.cuffee.order.service;

import com.mysite.cuffee.cart.entity.Cart;
import com.mysite.cuffee.cart.repository.CartRepository;
import com.mysite.cuffee.order.dto.OrderDto;
import com.mysite.cuffee.order.entity.Customer;
import com.mysite.cuffee.order.entity.OrderItem;
import com.mysite.cuffee.order.repository.OrderRepository;
import com.mysite.cuffee.products.entity.Coffee;
import com.mysite.cuffee.products.repository.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CoffeeRepository coffeeRepository;

    public OrderItem create(OrderDto orderDto) {
        Cart cart = cartRepository.findById(orderDto.getCartId())
                .orElseThrow(() -> new RuntimeException("장바구니를 찾을 수 없습니다."));

        Coffee coffee = coffeeRepository.findById(orderDto.getCoffeeId())
                .orElseThrow(() -> new RuntimeException("커피를 찾을 수 없습니다."));

        OrderItem orderItem = new OrderItem();
        orderItem.setCart(cart);
        orderItem.setCoffee(coffee);
        orderItem.setQuantity(orderDto.getQuantity());
        orderItem.setSubtotalPrice(coffee.getPrice() * orderDto.getQuantity());
        orderItem.setCreateDate(LocalDateTime.now());

        return orderRepository.save(orderItem);
    }


}
