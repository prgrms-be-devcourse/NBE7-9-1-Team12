package com.mysite.cuffee.products.controller;

import com.mysite.cuffee.products.dto.CoffeeResponseDto;
import com.mysite.cuffee.products.service.CoffeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {

    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping("/")
    public List<String> getAllCoffeeNames() {
        return coffeeService.getAllCoffeeNames();
    }



    //---------------------------------------------------------------
}
