package com.mysite.cuffee.customer.service;

import com.mysite.cuffee.cart.repository.CartRepository;
import com.mysite.cuffee.customer.entity.Customer;
import com.mysite.cuffee.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer findOrCreateCustomer(String customerEmail, String address, String zipcode) {
        return customerRepository.findByEmailAndAddressAndZipcode(customerEmail, address, zipcode)
                .orElseGet(() -> customerRepository.save(new Customer(customerEmail, address, zipcode)));
    }

}
