package com.mysite.cuffee.order.service;

import com.mysite.cuffee.cart.entity.Cart;
import com.mysite.cuffee.cart.entity.CartItem;
import com.mysite.cuffee.cart.repository.CartRepository;
import com.mysite.cuffee.order.dto.OrderDto;
import com.mysite.cuffee.order.entity.Customer;
import com.mysite.cuffee.order.entity.OrderItem;
import com.mysite.cuffee.order.entity.Purchase;
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

    public OrderItem createOrder(OrderDto orderDto) {

        Customer customer = customerRepository.findByEmail(orderDto.getCustomerEmail())
                .orElseGet(() -> {
                    Customer newCustomer = new Customer();
                    newCustomer.setEmail(orderDto.getCustomerEmail());
                    return customerRepository.save(newCustomer);
                });

        Cart cart = cartRepository.findById(orderDto.getCartId())
                .orElseThrow(() -> new RuntimeException("장바구니를 찾을 수 없습니다."));

        if (!cart.getOwnerEmail().equals(orderDto.getCustomerEmail())) {
            throw new RuntimeException("장바구니 소유자가 일치하지 않습니다.");
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setCustomer(customer);
        orderItem.setCart(cart);
        orderItem.setCreateDate(LocalDateTime.now());

        List<Purchase> purchases = new ArrayList<>();
        int totalAmount = 0;

        for (CartItem cartItem : cart.getItems()) {
            Purchase purchase = new Purchase();
            purchase.setOrderItem(orderItem);
            purchase.setProductId(cartItem.getProductId());
            purchase.setProductName(cartItem.getProductName());
            purchase.setUnitPrice(cartItem.getUnitPrice());
            purchase.setQuantity(cartItem.getQty());
            purchase.setSubtotalPrice(cartItem.getUnitPrice() * cartItem.getQty());

            purchases.add(purchase);
            totalAmount += purchase.getSubtotalPrice();
        }

        orderItem.setPurchases(purchases);
        orderItem.setTotalAmount(totalAmount);

        return orderRepository.save(orderItem);
    }
}
