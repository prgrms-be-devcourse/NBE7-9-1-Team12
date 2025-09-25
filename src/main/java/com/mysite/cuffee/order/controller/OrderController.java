package com.mysite.cuffee.order.controller;

import com.mysite.cuffee.cart.entity.Cart;
import com.mysite.cuffee.cart.service.CartService;
import com.mysite.cuffee.order.entity.OrderItem;
import com.mysite.cuffee.order.service.OrderService;
import com.mysite.global.exception.ServiceException;
import com.mysite.global.rsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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

    record PaymentRequest(
            @NotBlank @Email String customerEmail,
            @NotBlank String address,
            @NotBlank @Size(min = 5, max = 5) String zipcode,
            @NotNull Long cartId
    ) {}

    @PostMapping("/pay")
    @Transactional
    public RsData<OrderResponseBody> createOrder(
            @Valid @RequestBody PaymentRequest request){

        orderService.findOrCreateCustomer(request.customerEmail(), request.address(), request.zipcode());

        Cart cart = cartService.findByCartId(request.cartId())
                .orElseThrow(() -> new ServiceException("404-1", "장바구니를 찾을 수 없습니다."));

        orderService.validateCartOwner(cart, request.customerEmail());

        List<OrderItem> orderItems = orderService.createOrderItems(cart, request.customerEmail(), request.address(), request.zipcode());

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
