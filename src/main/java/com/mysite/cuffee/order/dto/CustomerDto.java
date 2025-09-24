package com.mysite.cuffee.order.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {

    @NotEmpty(message = "email은 필수항목입니다.")
    @Email
    private String email;

    @NotEmpty(message = "주소는 필수항목입니다.")
    private String address;

    @NotEmpty(message = "우편번호는 필수항목입니다.")
    @Size(min = 5, max=5)
    private String zipCode;
}
