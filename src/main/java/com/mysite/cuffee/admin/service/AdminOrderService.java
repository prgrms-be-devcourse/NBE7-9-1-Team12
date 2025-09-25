// com/mysite/cuffee/admin/order/AdminOrderService.java
package com.mysite.cuffee.admin.service;

import com.mysite.cuffee.admin.dto.AdminOrderDto;
import com.mysite.cuffee.order.entity.OrderItem;
import com.mysite.cuffee.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
// 관리자 주문 서비스
public class AdminOrderService {

    private final OrderRepository orderRepository;

    public List<AdminOrderDto.OrderResponse> findAllOrders() {
        List<OrderItem> allOrderItems = orderRepository.findAll();

        Map<Long, List<OrderItem>> groupedByCartId = allOrderItems.stream()
                .collect(Collectors.groupingBy(item -> item.getCart().getId()));

        List<AdminOrderDto.OrderResponse> orderResponses = new ArrayList<>();
        for (Map.Entry<Long, List<OrderItem>> entry : groupedByCartId.entrySet()) {
            Long cartId = entry.getKey();
            List<OrderItem> itemsInOrder = entry.getValue();

            OrderItem representativeItem = itemsInOrder.get(0);

            // record 생성자로 변경: .builder() -> new AdminOrderDto.OrderItemResponse(...)
            List<AdminOrderDto.OrderItemResponse> itemDtos = itemsInOrder.stream()
                    .map(item -> new AdminOrderDto.OrderItemResponse(
                            item.getOrderItemId(),
                            item.getCoffee().getCoffeeId(),
                            item.getCoffee().getName(),
                            item.getQuantity(),
                            item.getSubtotalPrice() / item.getQuantity(),
                            item.getSubtotalPrice()
                    ))
                    .collect(Collectors.toList());

            int totalAmount = itemDtos.stream()
                    .mapToInt(AdminOrderDto.OrderItemResponse::subtotalPrice)
                    .sum();

            // record 생성자로 변경: .builder() -> new AdminOrderDto.OrderResponse(...)
            orderResponses.add(
                    new AdminOrderDto.OrderResponse(
                            cartId,
                            representativeItem.getCustomerEmail(),
                            representativeItem.getShipToAddress(),
                            representativeItem.getShipToZipcode(),
                            representativeItem.getCreateDate(),
                            itemDtos,
                            totalAmount
                    )
            );
        }

        return orderResponses;
    }
}