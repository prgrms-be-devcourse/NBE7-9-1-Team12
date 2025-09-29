package com.mysite.cuffee.admin.controller;

import com.mysite.cuffee.admin.dto.AdminProductDto;
import com.mysite.cuffee.admin.service.AdminProductService;
import com.mysite.cuffee.products.dto.CoffeeResponseDto;
import com.mysite.cuffee.products.entity.Coffee;
import com.mysite.cuffee.products.service.CoffeeService;
import com.mysite.global.rsData.RsData;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

// 관리자 상품 관리 컨트롤러
@RestController
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductService adminProductService;

    @PostMapping("/add")
    @Operation(summary = "제품 등록", description = "새로운 제품을 등록합니다.")
    public RsData<Void> addProduct(
            @Valid @RequestBody AdminProductDto.CreateRequest rqBody
    ){
        adminProductService.addProduct(rqBody.name(), rqBody.price(), rqBody.contents(), rqBody.imageUrl(), rqBody.stock());
        return new RsData<>(
                "201-1",
                "제품이 등록되었습니다."
        );
    }

    @DeleteMapping("/{coffeeId}")
    @Operation(summary = "제품 삭제", description = "제품을 삭제합니다.")
    public RsData<String> deleteProduct(
            @PathVariable long coffeeId
    ){
        Coffee coffee = adminProductService.findById(coffeeId);
        String imageUrl = coffee.getImageUrl();

        adminProductService.deleteProduct(coffee);
        return new RsData<>(
                "204-1",
                "제품이 삭제 되었습니다.",
                imageUrl
        );
    }

    @PutMapping("/{coffeeId}")
    @Operation(summary = "제품 수정", description = "제품 정보를 수정합니다.")
    public RsData<CoffeeResponseDto> modifyProduct(
            @PathVariable long coffeeId,
            @Valid @RequestBody AdminProductDto.UpdateRequest rqBody
    ){
        Coffee coffee = adminProductService.findById(coffeeId);
        adminProductService.modifyProduct(coffee, rqBody.name(), rqBody.price(), rqBody.contents(), rqBody.imageUrl(), rqBody.stock());

        return new RsData<>(
                "200-1",
                "제품이 수정 되었습니다.",
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
}
