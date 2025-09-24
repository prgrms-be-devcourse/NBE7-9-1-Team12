package com.mysite.cuffee.order.entity;

import com.mysite.cuffee.cart.entity.Cart;
import com.mysite.cuffee.products.entity.Coffee;
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
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Purchase> purchases = new ArrayList<>();

    private int totalAmount;

    private LocalDateTime createDate;

}
