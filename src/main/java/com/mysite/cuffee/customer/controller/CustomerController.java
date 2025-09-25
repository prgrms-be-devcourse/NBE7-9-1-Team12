package com.mysite.cuffee.customer.controller;

import com.mysite.cuffee.cart.entity.Cart;
import com.mysite.cuffee.cart.repository.CartRepository;
import com.mysite.cuffee.cart.service.CartService;
import com.mysite.cuffee.customer.entity.Customer;
import com.mysite.cuffee.customer.service.CustomerService;
import com.mysite.global.exception.ServiceException;
import com.mysite.global.rsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/coffee")
public class CustomerController {

    private final CustomerService customerService;
    private final CartService cartService;
    private final CartRepository cartRepository;

    record OrderResponseBody(Long cartId, int totalAmount) {}

    record PaymentRequest(
            @NotBlank @Email String customerEmail,
            @NotBlank String address,
            @NotBlank @Size(min = 5, max = 5) String zipcode
    ) {}

    @PostMapping("/carts/{cartId}/customer")
    public RsData<Void> createCustomer(
            @PathVariable Long cartId,
            @Valid @RequestBody PaymentRequest rqBody){

        Customer customer = customerService.saveCustomer(rqBody.customerEmail, rqBody.address, rqBody.zipcode);
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow();
        cart.setCustomer(customer);

        return new RsData<>(
                "201-1",
                "주문이 성공적으로 완료되었습니다."
        );
    }
}
