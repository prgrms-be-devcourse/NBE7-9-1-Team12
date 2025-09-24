package com.mysite.cuffee.order.repository;

import com.mysite.cuffee.order.entity.Customer;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(@NotNull String customerEmail);
}
