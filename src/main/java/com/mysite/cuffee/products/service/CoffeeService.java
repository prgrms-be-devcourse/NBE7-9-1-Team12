package com.mysite.cuffee.products.service;

import com.mysite.cuffee.products.dto.CoffeeResponseDto;
import com.mysite.cuffee.products.entity.Coffee;
import com.mysite.cuffee.products.repository.CoffeeRepository;
import com.mysite.global.rsData.RsData;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public RsData<List<CoffeeResponseDto>> getAllCoffees() {
        List<Coffee> entities = coffeeRepository.findAll();
        List<CoffeeResponseDto> dtos = new ArrayList<>();

        for (Coffee c : entities) {
            dtos.add(toDto(c));
        }

        return new RsData<>(
                "200",
                "커피 목록 조회 성공 (" + dtos.size() + "건)",
                dtos
        );
    }

    public RsData<CoffeeResponseDto> getCoffeeById(long coffeeId) {
        Coffee entity = coffeeRepository.findById(coffeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coffee not found"));

        return new RsData<>(
                "200",
                "커피 단건 조회 성공",
                toDto(entity)
        );
    }

    private CoffeeResponseDto toDto(Coffee coffee) {
        return new CoffeeResponseDto(
                coffee.getCoffeeId(),
                coffee.getName(),
                coffee.getPrice(),
                coffee.getContents(),
                coffee.getStock(),
                coffee.getImageUrl()
        );
    }

    public void addProduct(String name, int price, String contents, String imageUrl, int stock) {
        Coffee coffee = new Coffee(name, price, contents, imageUrl, stock);
        coffeeRepository.save(coffee);
    }
}