package com.mysite.cuffee.products.repository;

import com.mysite.cuffee.products.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {

    boolean existsByName(String name);
}
