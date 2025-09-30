package com.mysite.cuffee.customer.controller;

import com.mysite.cuffee.customer.dto.customerDto;
import com.mysite.cuffee.customer.service.CustomerService;
import com.mysite.global.rsData.RsData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
@RequestMapping("/coffee")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/carts/{cartId}/customer")
    public RsData<Void> createCustomer(
            @PathVariable Long cartId,
            @Valid @RequestBody customerDto rqBody){

        customerService.setCartCustomer(
                cartId,
                rqBody.customerEmail(),
                rqBody.address(),
                rqBody.zipcode()
        );

        return new RsData<>(
                "201-1",
                "주문이 성공적으로 완료되었습니다."
        );
    }
}
