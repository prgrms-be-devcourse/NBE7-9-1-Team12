package com.mysite.cuffee.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record customerDto(
        @NotBlank @Email String customerEmail,
        @NotBlank String address,
        @NotBlank @Size(min = 5, max = 5) String zipcode
) {}
