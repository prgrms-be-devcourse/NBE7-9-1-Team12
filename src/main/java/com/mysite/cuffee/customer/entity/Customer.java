package com.mysite.cuffee.customer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(unique = true)
    private String email;

    private String address;

    private String zipcode;

    public Customer(String email, String address, String zipcode) {
        this.email = email;
        this.address = address;
        this.zipcode = zipcode;
    }


}
