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

    public void addToCart(int id) {
        Coffee coffee = coffeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id+"번 커피가 존재하지 않습니다."));

        //TODO: 장바구니에 커피 추가 로직 구현
        // orderItemRepository.save(item);

        System.out.println("장바구니에 추가됨: " + coffee.getName());
        //임시구현 해두었음
    }

    public void removeFromCart(int id) {
        //TODO: 장바구니에서 커피 삭제 로직 구현
        //        orderItemRepository.deleteByCoffeeId(id);
        System.out.println("장바구니에서 삭제됨: " + id);
    }


    /*      예진: getAllCoffeeNames(): 이름 조회, addToCart(id)  */


/*      명재님: getAllCoffeePrices(): 가격 조회, getAllCoffeeContents(): 설명 조회 */
}
