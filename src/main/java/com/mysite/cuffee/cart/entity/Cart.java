package com.mysite.cuffee.cart.entity;

import com.mysite.cuffee.order.entity.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cart", indexes = @Index(name = "ux_cart_key", columnList="cartKey", unique = true))
public class Cart{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    public int totalPrice() {
        return items.stream().mapToInt(i -> i.getUnitPrice() * i.getQty()).sum();
    }

    public void setOrderDate() {
        this.orderDate = LocalDateTime.now();
    }
}
