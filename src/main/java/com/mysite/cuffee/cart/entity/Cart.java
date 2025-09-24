package com.mysite.cuffee.cart.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "cart", indexes = @Index(name = "ux_cart_key", columnList="cartKey", unique = true))
public class Cart{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @Column(length = 200)
    private String ownerEmail;

    @LastModifiedDate
    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    public int totalAmount() {
        return items.stream().mapToInt(i -> i.getUnitPrice() * i.getQty()).sum();
    }

    public void setOwnerEmail(String email) {
        this.ownerEmail = email;
    }
}
