package com.mysite.cuffee.cart.dto;


import com.mysite.cuffee.cart.entity.CartItem;

import java.util.List;

public record CartDto(
    List<CartItem> items,
    int totalQuantity,
    int totalAmount
){}
