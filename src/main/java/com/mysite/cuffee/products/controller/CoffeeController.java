package com.mysite.cuffee.products.controller;

import com.mysite.cuffee.products.dto.CoffeeResponseDto;
import com.mysite.cuffee.products.service.CoffeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
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

    // [GET] /coffee/prices : 원두 별 가격 목록
    @GetMapping("/prices")
    public List<Integer> getAllCoffeePrices(){
        return coffeeService.getAllCoffeePrices();
    }

    // [GET] /coffee/contents : 원두 별 설명 목록
    @GetMapping("/contents")
    public List<String> getAllCoffeeContents(){
        return coffeeService.getAllCoffeeContents();
    }

    // [GET] /coffee/contents : 원두 별 이미지 목록
    @GetMapping("/images")
    public List<String> getAllCoffeeImages(){
        return coffeeService.getAllCoffeeImageUrls();
    }
}
