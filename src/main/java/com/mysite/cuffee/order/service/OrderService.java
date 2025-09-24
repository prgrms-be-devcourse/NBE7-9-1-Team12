package com.mysite.cuffee.order.service;

import com.mysite.cuffee.order.entity.Customer;
import com.mysite.cuffee.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public void createOrder(String email, String address, String zipcode){
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setAddress(address);
        customer.setZipcode(zipcode);
        this.orderRepository.save(customer);
    }


}
