package com.mysite.cuffee.customer.repository;

import com.mysite.cuffee.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
