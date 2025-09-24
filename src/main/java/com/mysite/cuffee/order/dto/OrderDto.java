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
    private String customerEmail;

    @NotNull
    private Long cartId;
}
