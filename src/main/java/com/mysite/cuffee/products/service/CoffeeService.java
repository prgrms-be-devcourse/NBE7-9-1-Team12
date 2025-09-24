package com.mysite.cuffee.products.service;

import com.mysite.cuffee.products.entity.Coffee;
import com.mysite.cuffee.products.repository.CoffeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoffeeService {
    /*
        예진: getAllCoffeeNames(): 이름 조회, addToCart(productId)
        명재님: getAllCoffeePrices(): 가격 조회, getAllCoffeeContents(): 설명 조회

     */
    private final CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public List<String> getAllCoffeeNames() {
        return coffeeRepository.findAll().stream()
                .map(Coffee::getName)
                .toList();
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