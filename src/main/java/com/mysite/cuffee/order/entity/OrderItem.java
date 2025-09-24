package com.mysite.cuffee.order.entity;

import com.mysite.cuffee.products.entity.Coffee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderItemId;

//    @ManyToOne
//    @JoinColumn(name = "cart_key")
//    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "coffee_id")
    private Coffee coffee;

    private int quantity;

    private int subtotalPrice;
}
