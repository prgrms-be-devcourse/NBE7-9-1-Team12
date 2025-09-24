package com.mysite.cuffee.products.service;

import com.mysite.cuffee.products.entity.Coffee;
import com.mysite.cuffee.products.repository.CoffeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public List<String> getAllCoffeeNames() {
        return coffeeRepository.findAll().stream()
                .map(Coffee::getName)
                .toList();
    }

    public void addToCart(int id){
        Coffee coffee = coffeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id+"번 커피가 존재하지 않습니다."));

        //TODO: 장바구니에 커피 추가 로직 구현
        //  orderItemRepository.save(item);

        System.out.println("장바구니에 추가됨: " + coffee.getName());
        //임시구현 해두었음
    }

    public void removeFromCart(int id){
        //TODO: 장바구니에서 커피 삭제 로직 구현
        //  orderItemRepository.deleteByCoffeeId(id);
        coffeeRepository.deleteById(id);
    }

    // 가격 목록 조회
    public List<Integer> getAllCoffeePrices() {
        List<Coffee> coffees = coffeeRepository.findAll();
        List<Integer> prices = new ArrayList<>();
        for(Coffee coffee : coffees) {
            prices.add(coffee.getPrice());
        }
        return prices;
    }

    // 설명 목록 조회
    public List<String> getAllCoffeeContents() {
        List<Coffee>  coffees = coffeeRepository.findAll();
        List<String> contentList = new ArrayList<>();
        for(Coffee coffee : coffees){
            contentList.add(coffee.getContents());
        }
        return contentList;
    }

    // 이미지 목록 조회
    public List<String> getAllCoffeeImageUrls() {
        List<Coffee> coffees = coffeeRepository.findAll();
        List<String> imageUrls = new ArrayList<>();
        for(Coffee coffee : coffees){
            imageUrls.add(coffee.getImageUrl());
        }
        return imageUrls;
    }
}