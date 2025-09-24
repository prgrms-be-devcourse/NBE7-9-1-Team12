package com.mysite.cuffee.order.controller;

import com.mysite.cuffee.order.dto.OrderDto;
import com.mysite.cuffee.order.entity.OrderItem;
import com.mysite.cuffee.order.repository.OrderRepository;
import com.mysite.cuffee.order.service.OrderService;
import com.mysite.global.rsData.RsData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/coffee")
public class OrderController {

    private final OrderService orderService;

    record OrderResponseBody(Long cartId, int totalAmount, int itemCount) {}

    @PostMapping("/pay")
    public RsData<OrderResponseBody> createOrder(@Valid @RequestBody OrderDto orderDto){

        List<OrderItem> orderItems = orderService.createOrder(orderDto);

        int totalAmount = orderItems.stream()
                .mapToInt(OrderItem::getSubtotalPrice)
                .sum();

        return new RsData<>(
                "201-1",
                "주문이 성공적으로 완료되었습니다.",
                new OrderResponseBody(
                        orderDto.getCartId(),
                        totalAmount,
                        orderItems.size()
                )
        );
    }

}
