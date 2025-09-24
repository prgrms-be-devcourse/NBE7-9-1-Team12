package com.mysite.cuffee.order.entity;

import com.mysite.cuffee.products.entity.Coffee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;

    @ManyToOne
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;

    private Long productId;

    private String productName;

    private int unitPrice;

    private int quantity;

    private int subtotalPrice;
}