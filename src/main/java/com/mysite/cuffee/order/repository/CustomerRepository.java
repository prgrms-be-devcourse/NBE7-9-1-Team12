package com.mysite.cuffee.order.repository;

import com.mysite.cuffee.order.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
