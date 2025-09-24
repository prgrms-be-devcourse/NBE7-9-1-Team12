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

    @PostMapping("/order/{id}")
    public ResponseEntity<String> addToCart(@PathVariable int id) {
        coffeeService.addToCart(id);
        return ResponseEntity.ok("커피가 성공적으로 담겼습니다.");
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<String> removeFromCart(@PathVariable int id){
        coffeeService.removeFromCart(id);
        return ResponseEntity.ok("커피가 성공적으로 삭제되었습니다.");
    }

    //---------------------------------------------------------------
}
