package com.mysite.cuffee.cart.dto;

import java.util.List;

public class CartDtoRes {
    public record ItemLine(
            Long itemId,
            Long productId,
            String name,
            int unitPrice,
            int qty,
            int lineTotal
    ) {}

    public record GetCartSummaryResBody(
            List<ItemLine> items,
            int totalAmount
    ) {}
}
