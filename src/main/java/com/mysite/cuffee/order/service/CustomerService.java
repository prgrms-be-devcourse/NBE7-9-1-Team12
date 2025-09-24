package com.mysite.cuffee.order.service;

import com.mysite.cuffee.order.dto.CustomerDto;
import com.mysite.cuffee.order.entity.Customer;
import com.mysite.cuffee.order.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer create(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setEmail(customerDto.getEmail());
        customer.setAddress(customerDto.getAddress());
        customer.setZipcode(customerDto.getZipcode());
        return customerRepository.save(customer);
    }


}
