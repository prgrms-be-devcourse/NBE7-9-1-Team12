package com.mysite.cuffee.products.repository;

import com.mysite.cuffee.products.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Integer> {
}
