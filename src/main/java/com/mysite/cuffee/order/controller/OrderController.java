package com.mysite.cuffee.order.controller;

import com.mysite.cuffee.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;


@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;

}
