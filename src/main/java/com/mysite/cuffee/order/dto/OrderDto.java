package com.mysite.cuffee.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    @NotNull
    private String customerEmail;  // Customer ID 대신 email 사용 (Cart.ownerEmail과 매칭)

    @NotNull
    private Long cartId;
}
