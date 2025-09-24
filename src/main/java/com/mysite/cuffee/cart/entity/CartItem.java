package com.mysite.cuffee.cart.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name="cart_item",
        indexes=@Index(name="ux_cart_product", columnList="cart_id, productId", unique = true))
public class CartItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "cart_id")
    private Cart cart;

    private Integer productId;
    private String  productName;
    private int     unitPrice;
    private int     qty;
}