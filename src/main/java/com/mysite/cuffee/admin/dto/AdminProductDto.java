package com.mysite.cuffee.admin.dto;

// 관리자 상품 요청 DTO
public class AdminProductDto {

    public record CreateRequest(
            String name,
            String contents,
            int price,
            String imageUrl
    ) {}


}
