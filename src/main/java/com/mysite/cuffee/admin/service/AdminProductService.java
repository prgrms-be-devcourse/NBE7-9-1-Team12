package com.mysite.cuffee.admin.service;

import com.mysite.cuffee.admin.dto.AdminProductDto;
import com.mysite.cuffee.products.entity.Coffee;
import com.mysite.cuffee.products.repository.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 관리자 상품 관리 서비스
@Service
@RequiredArgsConstructor
@Transactional
public class AdminProductService {
    public final CoffeeRepository coffeeRepository;

    public void createProduct(AdminProductDto.CreateRequest request) {
        if (coffeeRepository.existsByName(request.name())) {
            throw new IllegalStateException("이미 존재하는 원두 이름입니다.");
        }

        Coffee newCoffee = new Coffee();
        newCoffee.setName(request.name());
        newCoffee.setContents(request.contents());
        newCoffee.setPrice(request.price());
        newCoffee.setImageUrl(request.imageUrl());
        // newCoffee.setStock(request.stock());

        coffeeRepository.save(newCoffee);
    }

    public void deleteProduct(Long productId) {
        if (!coffeeRepository.existsById(productId)) {
            throw new IllegalArgumentException("해당 ID의 상품을 찾을 수 없습니다: " + productId);
        }

        coffeeRepository.deleteById(productId);
    }

    public void updateStock(Long productId, int stock) {
        Coffee coffee = coffeeRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 상품을 찾을 수 없습니다: " + productId));

        coffee.setStock(stock);
    }
}
