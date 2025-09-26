package com.mysite.cuffee.customer.entity;

import com.mysite.cuffee.cart.entity.Cart;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @OneToMany(mappedBy = "customer")
    private List<Cart> carts = new ArrayList<>();

    private String email;

    private String address;

    private String zipcode;

    public Customer(String email, String address, String zipcode) {
        this.email = email;
        this.address = address;
        this.zipcode = zipcode;
    }


}
