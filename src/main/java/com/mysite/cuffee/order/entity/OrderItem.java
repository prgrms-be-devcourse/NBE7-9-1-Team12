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
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "coffee_id")
    private Coffee coffee;

    private int quantity;

    private int subtotalPrice;

    private LocalDateTime createDate;

    // 주문 당시 주소 스냅샷 (고객 정보 변경과 무관하게 보관)
    private String shipToAddress;
    private String shipToZipcode;

}
