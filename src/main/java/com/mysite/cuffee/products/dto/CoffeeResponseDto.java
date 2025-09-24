package com.mysite.cuffee.products.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeResponseDto {

    private int coffeeId;
    private String name;
    private int price;
    private String contents;
    private String imageUrl;
}