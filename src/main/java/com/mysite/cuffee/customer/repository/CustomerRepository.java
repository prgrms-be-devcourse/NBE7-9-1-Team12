package com.mysite.cuffee.customer.repository;

import com.mysite.cuffee.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmailAndAddressAndZipcode(String email, String address, String zipcode);
}
