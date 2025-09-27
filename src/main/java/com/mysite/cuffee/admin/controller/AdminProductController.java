package com.mysite.cuffee.admin.controller;

import com.mysite.cuffee.admin.dto.AdminProductDto;
import com.mysite.cuffee.admin.service.AdminProductService;
import com.mysite.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

// 관리자 상품 관리 컨트롤러
@RestController
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductService adminProductService;
    @PostMapping
    public RsData<Void> createProduct(
            @RequestBody AdminProductDto.CreateRequest request
    ) {
        adminProductService.createProduct(request);
        return new RsData<>(
                "201-0",
                "새로운 상품이 성공적으로 등록되었습니다."
        );
    }

    @DeleteMapping("/{productId}")
    public RsData<Void> deleteProduct(
            @PathVariable Long productId
    ) {
        adminProductService.deleteProduct(productId);
        return new RsData<>(
                "200-0",
                "상품이 성공적으로 삭제되었습니다."
        );
    }
}
