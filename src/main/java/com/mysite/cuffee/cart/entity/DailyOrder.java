package com.mysite.cuffee.cart.entity;

import com.mysite.global.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class DailyOrder extends BaseEntity {
    private int totalPrice;
    private String coffeeName;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
}
