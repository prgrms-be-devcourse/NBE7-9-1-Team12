package com.mysite.cuffee.admin.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

// 관리자 주문 응답 DTO
// dto/AdminOrderResponse.java
@Getter
@Builder
public class AdminOrderDto {
    public record OrderResponse(
            Long orderId, // cartId를 주문 ID로 사용
            String customerEmail,
            String shipToAddress,
            String shipToZipcode,
            LocalDateTime orderDate,
            List<OrderItemResponse> orderItems,
            int totalAmount
    ) {}

    public record OrderItemResponse(
            Long orderItemId,
            Long productId,
            String productName,
            int quantity,
            int unitPrice,
            int subtotalPrice
    ) {}
}
