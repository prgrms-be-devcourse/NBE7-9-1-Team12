package com.mysite.cuffee.products.service;

import com.mysite.cuffee.products.entity.Coffee;
import com.mysite.cuffee.products.repository.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    public List<Coffee> getAllCoffees() {
        List<Coffee> entities = coffeeRepository.findAll();
        return entities;
    }

    public Coffee getCoffeeById(long coffeeId) {
        Coffee entity = coffeeRepository.findById(coffeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coffee not found"));
        return entity;
    }

    public void addProduct(String name, int price, String contents, String imageUrl, int stock) {
        Coffee coffee = new Coffee(name, price, contents, imageUrl, stock);
        coffeeRepository.save(coffee);
    }

    public Coffee findById(long coffeeId) {
        return coffeeRepository.findById(coffeeId).get();
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