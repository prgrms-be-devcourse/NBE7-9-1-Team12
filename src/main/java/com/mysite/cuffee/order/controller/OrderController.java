package com.mysite.cuffee.order.controller;

import com.mysite.cuffee.cart.entity.Cart;
import com.mysite.cuffee.cart.service.CartService;
import com.mysite.cuffee.order.dto.CustomerDto;
import com.mysite.cuffee.order.entity.OrderItem;
import com.mysite.cuffee.order.service.OrderService;
import com.mysite.global.exception.ServiceException;
import com.mysite.global.rsData.RsData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/coffee")
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;

    record OrderResponseBody(Long cartId, int totalAmount) {}

    record PaymentRequest(@Valid CustomerDto customer, Long cartId) {}

    @PostMapping("/pay")
    @Transactional
    public RsData<OrderResponseBody> createOrder(
            @Valid @RequestBody PaymentRequest request){

        orderService.findOrCreateCustomer(request.customer());

        Cart cart = cartService.findByCartId(request.cartId())
                .orElseThrow(() -> new ServiceException("404-1", "장바구니를 찾을 수 없습니다."));

        orderService.validateCartOwner(cart, request.customer().getEmail());

        List<OrderItem> orderItems = orderService.createOrderItems(cart, request.customer());

        int totalAmount = orderItems.stream()
                .mapToInt(OrderItem::getSubtotalPrice)
                .sum();

        return new RsData<>(
                "201-1",
                "주문이 성공적으로 완료되었습니다.",
                new OrderResponseBody(
                        request.cartId(),
                        totalAmount
                )
        );
    }
}
