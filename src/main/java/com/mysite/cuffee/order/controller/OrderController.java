package com.mysite.cuffee.order.controller;

import com.mysite.cuffee.order.dto.OrderDto;
import com.mysite.cuffee.order.entity.OrderItem;
import com.mysite.cuffee.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/coffee")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public OrderItem createOrder(@Valid @RequestBody OrderDto orderDto){

        OrderItem orderItem = orderService.createOrder(orderDto);
        return orderItem;
    }

}
