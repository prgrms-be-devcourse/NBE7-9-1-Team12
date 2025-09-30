package com.mysite.cuffee.customer.service;

import com.mysite.cuffee.cart.entity.Cart;
import com.mysite.cuffee.cart.repository.CartRepository;
import com.mysite.cuffee.customer.entity.Customer;
import com.mysite.cuffee.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;

    public Customer findOrCreateCustomer(String customerEmail, String address, String zipcode) {
        return customerRepository.findByEmailAndAddressAndZipcode(customerEmail, address, zipcode)
                .orElseGet(() -> customerRepository.save(new Customer(customerEmail, address, zipcode)));
    }

    public void setCartCustomer(Long cartId, String customerEmail, String address, String zipcode) {
        Customer customer = findOrCreateCustomer(customerEmail, address, zipcode);
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("장바구니가 존재하지 않습니다. ID: " + cartId));

        cart.setCustomer(customer);
        cartRepository.save(cart);
    }

}
