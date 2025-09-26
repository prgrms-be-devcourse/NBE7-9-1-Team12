// com/mysite/cuffee/admin/order/AdminOrderService.java
package com.mysite.cuffee.admin.service;

import com.mysite.cuffee.admin.dto.AdminOrderDto;
import com.mysite.cuffee.cart.entity.Cart;
import com.mysite.cuffee.cart.repository.CartRepository;
import com.mysite.cuffee.customer.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
// 관리자 주문 서비스
public class AdminOrderService {

    private final CartRepository cartRepository;

    public List<AdminOrderDto.OrderResponse> findAllOrders() {
        return mapCartsToOrderResponses(
                cartRepository.findAll().stream()
                        .filter(cart -> cart.getOrderDate() != null)
                        .collect(Collectors.toList())
        );
    }


    public List<AdminOrderDto.OrderResponse> findDailyBatchOrders() {
        LocalDateTime batchEndTime = LocalDateTime.now().with(LocalTime.of(14, 0, 0));
        LocalDateTime batchStartTime = batchEndTime.minusDays(1);

        return mapCartsToOrderResponses(
                cartRepository.findByOrderDateBetween(batchStartTime, batchEndTime)
        );
    }

    private List<AdminOrderDto.OrderResponse> mapCartsToOrderResponses(List<Cart> carts) {
        return carts.stream()
                .map(cart -> {
                    //주문자 정보 추출
                    Customer customer = cart.getCustomer();

                    String customerEmail = (customer != null) ? customer.getEmail() : "N/A";
                    String shipToAddress = (customer != null) ? customer.getAddress() : "N/A";
                    String shipToZipcode = (customer != null) ? customer.getZipcode() : "N/A";

                    // 주문 상품 목록 생성
                    List<AdminOrderDto.OrderItemResponse> itemDtos = cart.getItems().stream()
                            .map(item -> new AdminOrderDto.OrderItemResponse(
                                    item.getId(), // CartItem ID를 OrderItem ID로 사용
                                    item.getProductId(),
                                    item.getProductName(),
                                    item.getQty(),
                                    item.getUnitPrice(),
                                    item.getUnitPrice() * item.getQty() // subtotalPrice
                            ))
                            .collect(Collectors.toList());

                    int totalAmount = cart.totalPrice();

                    // 추출된 모든 정보 조합 후 Cart ID를 Order ID로 사용하는 OrderResponse 생성
                    return new AdminOrderDto.OrderResponse(
                            cart.getId(),
                            customerEmail,
                            shipToAddress,
                            shipToZipcode,
                            cart.getOrderDate(),
                            itemDtos,
                            totalAmount
                    );
                })
                .collect(Collectors.toList());
    }
}