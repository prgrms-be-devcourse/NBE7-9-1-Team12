package com.mysite.cuffee.order.service;

import com.mysite.cuffee.cart.entity.Cart;
import com.mysite.cuffee.cart.entity.CartItem;
import com.mysite.cuffee.cart.repository.CartRepository;
import com.mysite.cuffee.order.dto.OrderDto;
import com.mysite.cuffee.order.entity.Customer;
import com.mysite.cuffee.order.entity.OrderItem;
import com.mysite.cuffee.order.repository.CustomerRepository;
import com.mysite.cuffee.order.repository.OrderRepository;
import com.mysite.cuffee.products.entity.Coffee;
import com.mysite.cuffee.products.repository.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final CoffeeRepository coffeeRepository;

    public List<OrderItem> createOrder(OrderDto orderDto) {

        Customer customer = customerRepository.findByEmail(orderDto.getCustomerEmail())
                .orElseGet(() -> {
                    Customer newCustomer = new Customer();
                    newCustomer.setEmail(orderDto.getCustomerEmail());
                    newCustomer.setAddress(orderDto.getAddress());
                    newCustomer.setZipcode(orderDto.getZipcode());
                    return customerRepository.save(newCustomer);
                });

        customer.setAddress(orderDto.getAddress());
        customer.setZipcode(orderDto.getZipcode());

        Cart cart = cartRepository.findById(orderDto.getCartId())
                .orElseThrow(() -> new RuntimeException("장바구니를 찾을 수 없습니다."));

        if (!cart.getCustomer().getEmail()
                .equals(orderDto.getCustomerEmail())) {
            throw new RuntimeException("장바구니 소유자가 일치하지 않습니다.");
        }

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            Coffee coffee = coffeeRepository.findById(cartItem.getProductId())
                    .orElseThrow(() -> new RuntimeException("커피 상품을 찾을 수 없습니다."));

            OrderItem orderItem = new OrderItem();
            orderItem.setCart(cart);
            orderItem.setCoffee(coffee);
            orderItem.setQuantity(cartItem.getQty());
            orderItem.setSubtotalPrice(cartItem.getUnitPrice() * cartItem.getQty());
            orderItem.setCreateDate(LocalDateTime.now());

            orderItems.add(orderItem);
        }

        return orderRepository.saveAll(orderItems);
    }
}
