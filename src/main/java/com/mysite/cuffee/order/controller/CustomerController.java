package com.mysite.cuffee.order.controller;

import com.mysite.cuffee.order.dto.CustomerDto;
import com.mysite.cuffee.order.entity.Customer;
import com.mysite.cuffee.order.service.CustomerService;
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

    @PostMapping("/user")
    public Customer createCustomer(@Valid @RequestBody CustomerDto customerDto){

        Customer customer = customerService.create(customerDto);
        return customer;
    }
}
