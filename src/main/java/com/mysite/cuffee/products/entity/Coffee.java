package com.mysite.cuffee.products.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coffee_id")
    private long coffeeId;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(name = "contents", length =1000)
    private String contents;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(nullable = false)
    private int stock;

    public Coffee(String name, int price, String contents, String imageUrl, int stock) {
        this.name = name;
        this.price = price;
        this.contents = contents;
        this.imageUrl = imageUrl;
        this.stock = stock;
    }
}
