package com.mysite.cuffee.products.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class CoffeeResponseDto {

    private long coffeeId;
    private String name;
    private int price;
    private String contents;
    private int stock;
    private String imageUrl;
}