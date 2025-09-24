package com.mysite.cuffee.cart.entity;

import com.mysite.global.jpa.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "cart", indexes = @Index(name = "ux_cart_key", columnList="cartKey", unique = true))
public class Cart extends BaseEntity {
    @Column(nullable = false, unique = true, length = 40)
    private String cartKey;

    private LocalDateTime checkOutDate;

    @Column(length = 200)
    private String ownerEmail;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();
}
