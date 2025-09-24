package com.mysite.cuffee.order.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String address;

    @NotEmpty
    @Size(min = 5, max=5)
    private String zipcode;
}
