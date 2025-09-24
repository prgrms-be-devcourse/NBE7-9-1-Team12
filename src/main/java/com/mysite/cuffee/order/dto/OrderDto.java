package com.mysite.cuffee.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {

    @NotNull(message = "장바구니 ID는 필수입니다.")
    private Long cartId;

    @NotNull(message = "커피 ID는 필수입니다.")
    private Long coffeeId;

    @Min(value = 1, message = "수량은 1개 이상이여야 합니다.")
    private int quantity;
}
