package com.mysite.cuffee.customer.repository;

import com.mysite.cuffee.customer.entity.Customer;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
