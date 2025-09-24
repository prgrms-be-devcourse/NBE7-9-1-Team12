package com.mysite.cuffee.products.service;

import com.mysite.cuffee.products.dto.CoffeeResponseDto;
import com.mysite.cuffee.products.entity.Coffee;
import com.mysite.cuffee.products.repository.CoffeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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
/*      명재님: getAllCoffeePrices(): 가격 조회, getAllCoffeeContents(): 설명 조회 */
}
