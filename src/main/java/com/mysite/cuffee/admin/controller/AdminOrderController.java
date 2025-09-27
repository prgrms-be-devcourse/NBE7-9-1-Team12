package com.mysite.cuffee.admin.controller;


import com.mysite.cuffee.admin.dto.AdminOrderDto;
import com.mysite.cuffee.admin.service.AdminOrderService;
import com.mysite.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/orders")
// 관리자 주문 관리 컨트롤러
public class AdminOrderController {

    private final AdminOrderService adminOrderService;
    @GetMapping
    public RsData<List<AdminOrderDto.OrderResponse>> findAllOrders() {
        List<AdminOrderDto.OrderResponse> orders = adminOrderService.findAllOrders();

        return new RsData<>(
                "200-1",
                "전체 주문 목록 조회 성공",
                orders
        );
    }

    @GetMapping("/dailyBatch")
    public RsData<List<AdminOrderDto.OrderResponse>> findDailyBatchOrders() {
        List<AdminOrderDto.OrderResponse> orders = adminOrderService.findDailyBatchOrders();
        return new RsData<>(
                "200-2",
                "일일 배송 대상 주문 조회 성공",
                orders
        );
    }



}
