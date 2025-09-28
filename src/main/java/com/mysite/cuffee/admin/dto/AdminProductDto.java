package com.mysite.cuffee.admin.dto;

import jakarta.validation.constraints.PositiveOrZero;

// 관리자 상품 요청 DTO
public class AdminProductDto {

    public record CreateRequest(
            String name,
            String contents,
            int price,
            String imageUrl
            // @PositiveOrZero
            // int stock
    ) {}

    public record UpdateStockRequest(
            @PositiveOrZero
            int stock
    ) {}
}
