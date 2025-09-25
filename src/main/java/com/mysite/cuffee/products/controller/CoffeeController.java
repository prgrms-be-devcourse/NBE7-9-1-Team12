package com.mysite.cuffee.products.controller;

import com.mysite.cuffee.products.dto.CoffeeResponseDto;
import com.mysite.cuffee.products.service.CoffeeService;
import com.mysite.global.rsData.RsData;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {

    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping("/products")
    public RsData<List<CoffeeResponseDto>> getAllCoffees() {
        return coffeeService.getAllCoffees();
    }

    @GetMapping("/{coffeeId}")
    public RsData<CoffeeResponseDto> getCoffee(@PathVariable long coffeeId) {
        return coffeeService.getCoffeeById(coffeeId);
    }
}