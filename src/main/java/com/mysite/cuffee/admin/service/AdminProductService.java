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

    public void addProduct(String name, int price, String contents, String imageUrl, int stock) {
        Coffee coffee = new Coffee(name, price, contents, imageUrl, stock);
        coffeeRepository.save(coffee);
    }

    public Coffee findById(long coffeeId) {
        return coffeeRepository.findById(coffeeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 상품을 찾을 수 없습니다: " + coffeeId));
    }

    public void deleteProduct(Coffee coffee) {
        coffeeRepository.delete(coffee);
    }

    public void modifyProduct(Coffee coffee, String name, int price, String contents, String imageUrl, int stock) {
        coffee.setName(name);
        coffee.setPrice(price);
        coffee.setContents(contents);
        coffee.setImageUrl(imageUrl);
        coffee.setStock(stock);
        coffeeRepository.save(coffee);
    }
}
