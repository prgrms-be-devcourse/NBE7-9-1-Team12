package com.mysite.cuffee.order.dto;

import com.mysite.cuffee.products.entity.Coffee;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {

//    private Cart cart;

    @NotNull(message = "커피를 하나 이상 주문해야 합니다.")
    private Coffee coffee;

    @Min(value = 1, message = "수량은 1개 이상이여야 합니다.")
    private int quantity;

    @Min(value = 0)
    private int subtotalPrice;
}
