package com.mysite.cuffee.order.entity;

import com.mysite.cuffee.cart.entity.Cart;
import com.mysite.cuffee.products.entity.Coffee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Coffee coffee;

    private int quantity;

    private int subtotalPrice;

    private LocalDateTime createDate;

}
