package com.mysite.cuffee.products.controller;

import com.mysite.cuffee.media.service.MediaService;
import com.mysite.cuffee.products.dto.CoffeeResponseDto;
import com.mysite.cuffee.products.entity.Coffee;
import com.mysite.cuffee.products.service.CoffeeService;
import com.mysite.global.rsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/coffee")
@RequiredArgsConstructor
public class CoffeeController {

    private final CoffeeService coffeeService;
    private final MediaService mediaService;

    @GetMapping("/products")
    public RsData<List<CoffeeResponseDto>> getAllCoffees() {
        List<Coffee> coffees = coffeeService.getAllCoffees();
        List<CoffeeResponseDto> dtos = new ArrayList<>();

        for (Coffee coffee : coffees) {
            dtos.add(new CoffeeResponseDto(
                    coffee.getCoffeeId(),
                    coffee.getName(),
                    coffee.getPrice(),
                    coffee.getContents(),
                    coffee.getStock(),
                    coffee.getImageUrl()
            ));
        }
        return new RsData<>(
                "200-1",
                "커피 목록 조회 성공 (%s건)".formatted(coffees.size()),
                dtos
        );
    }

    @GetMapping("/{coffeeId}")
    public RsData<CoffeeResponseDto> getCoffee(@PathVariable long coffeeId) {
        Coffee coffee = coffeeService.getCoffeeById(coffeeId);
        return new RsData<>(
                "200-2",
                "커피 단건 조회 성공",
                new CoffeeResponseDto(
                        coffee.getCoffeeId(),
                        coffee.getName(),
                        coffee.getPrice(),
                        coffee.getContents(),
                        coffee.getStock(),
                        coffee.getImageUrl()
                )
        );
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

    @DeleteMapping("/products/{coffeeId}")
    public RsData<Void> deleteProduct(
            @PathVariable long coffeeId
    ){
        Coffee coffee = coffeeService.findById(coffeeId);
        String imageUrl = coffee.getImageUrl();

        if (imageUrl != null && !imageUrl.isBlank()) {
            mediaService.deleteByImageUrl(imageUrl);
        }

        coffeeService.deleteProduct(coffee);
        return new RsData<>(
                "204-1",
                "제품이 삭제 되었습니다."
        );
    }
}