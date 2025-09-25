package com.mysite.cuffee.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    @NotNull
    private String customerEmail;

<<<<<<< HEAD
    @NotEmpty
    private String address;

    @NotEmpty @Size(min = 5, max = 5)
    private String zipcode;

    @NotNull
=======
    @NotNull(message = "장바구니 ID는 필수입니다.")

>>>>>>> origin/main
    private Long cartId;
}
