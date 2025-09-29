package com.mysite.cuffee.admin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

// 관리자 상품 요청 DTO
public class AdminProductDto {

    public record CreateRequest(
            @NotBlank String name,
            @Positive int price,
            @NotBlank String contents,
            @NotBlank String imageUrl,
            @PositiveOrZero int stock
    ) {}

    public record UpdateRequest(
            @NotBlank String name,
            @Positive int price,
            @NotBlank String contents,
            @NotBlank String imageUrl,
            @PositiveOrZero int stock
    ) {}
}
