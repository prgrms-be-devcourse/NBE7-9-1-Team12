package com.mysite.cuffee.products.controller;

import com.mysite.cuffee.products.dto.CoffeeResponseDto;
import com.mysite.cuffee.products.service.CoffeeService;
import com.mysite.global.rsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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

    record CoffeeRequest(
            @NotBlank String name,
            @Positive int price,
            @NotBlank String contents,
            @NotBlank String imageUrl,
            @PositiveOrZero int stock
    ) {}

    @PostMapping("/products/add")
    public RsData<Void> addProduct(
            @Valid @RequestBody CoffeeRequest rqBody
    ){
        coffeeService.addProduct(rqBody.name, rqBody.price, rqBody.contents, rqBody.imageUrl, rqBody.stock);
        return new RsData<>(
                "201-1",
                "제품이 등록되었습니다."
        );
    }
}