package com.mysite.cuffee.order.controller;

import com.mysite.cuffee.order.dto.CustomerDto;
import com.mysite.cuffee.order.entity.Customer;
import com.mysite.cuffee.order.service.CustomerService;
import com.mysite.global.rsData.RsData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/coffee")
public class CustomerController {

    private final CustomerService customerService;

    record CustomerResponseBody(String email, String address, String zipcode) {}

    @PostMapping("/user")
    public RsData<CustomerResponseBody> createCustomer(@Valid @RequestBody CustomerDto customerDto){

        Customer customer = customerService.create(customerDto);
        return new RsData<>(
                "201-1",
                "고객 정보 등록이 성공적으로 완료되었습니다.",
                new CustomerResponseBody(
                        customer.getEmail(),
                        customer.getAddress(),
                        customer.getZipcode()
                )
        );
    }
}
