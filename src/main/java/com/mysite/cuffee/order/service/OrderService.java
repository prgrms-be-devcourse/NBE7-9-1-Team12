package com.mysite.cuffee.order.service;

import com.mysite.cuffee.cart.entity.Cart;
import com.mysite.cuffee.cart.entity.CartItem;
import com.mysite.cuffee.cart.service.CartService;
import com.mysite.cuffee.order.dto.CustomerDto;
import com.mysite.cuffee.order.entity.Customer;
import com.mysite.cuffee.order.entity.OrderItem;
import com.mysite.cuffee.order.repository.CustomerRepository;
import com.mysite.cuffee.order.repository.OrderRepository;
import com.mysite.cuffee.products.entity.Coffee;
import com.mysite.cuffee.products.repository.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CoffeeRepository coffeeRepository;
    private final CartService cartService;
    private final CustomerRepository customerRepository;


    public void findOrCreateCustomer(CustomerDto customerDto) {
        customerRepository.findByEmail(customerDto.getEmail())
                .orElseGet(() -> createCustomer(customerDto));
    }

    public Customer createCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setEmail(customerDto.getEmail());
        customer.setAddress(customerDto.getAddress());
        customer.setZipcode(customerDto.getZipcode());
        return customerRepository.save(customer);
    }

    public void validateCartOwner(Cart cart, String customerEmail) {
        if (cart.getCustomer() != null && !cart.getCustomer().getEmail().equals(customerEmail)) {
            throw new IllegalArgumentException("장바구니 소유자가 일치하지 않습니다.");
        }
    }

    public List<OrderItem> createOrderItems(Cart cart, CustomerDto customerDto) {
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = createSingleOrderItem(cart, cartItem, customerDto);
            orderItems.add(orderItem);
        }

        return orderRepository.saveAll(orderItems);
    }

    private OrderItem createSingleOrderItem(Cart cart, CartItem cartItem, CustomerDto customerDto) {
        Coffee coffee = coffeeRepository.findById(cartItem.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("커피 상품을 찾을 수 없습니다."));

        OrderItem orderItem = new OrderItem();
        orderItem.setCart(cart);
        orderItem.setCustomerEmail(customerDto.getEmail());
        orderItem.setCoffee(coffee);
        orderItem.setQuantity(cartItem.getQty());
        orderItem.setSubtotalPrice(cartItem.getUnitPrice() * cartItem.getQty());
        orderItem.setCreateDate(LocalDateTime.now());

        // 주소 스냅샷 저장 (OrderItem에 주문 당시 주소 정보 보관)
        orderItem.setShipToAddress(customerDto.getAddress());
        orderItem.setShipToZipcode(customerDto.getZipcode());

        return orderItem;
    }

}
