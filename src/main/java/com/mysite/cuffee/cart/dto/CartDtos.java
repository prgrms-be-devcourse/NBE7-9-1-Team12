package com.mysite.cuffee.cart.dto;


import java.util.List;

public class CartDtos {
    public record CartItemResponse(
            Long itemId,
            Integer productId,
            String name,
            int unitPrice,
            int qty,
            int lineTotal
    ) {
    }

    public record CartResponse(
            String cartKey,
            List<CartItemResponse> items,
            int totalQuantity,
            int totalAmount
    ) {
    }
}
