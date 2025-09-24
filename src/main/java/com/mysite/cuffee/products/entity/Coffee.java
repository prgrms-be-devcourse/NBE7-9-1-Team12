package com.mysite.cuffee.products.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "coffee")
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coffee_id")
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(name = "contents", length =1000)
    private String contents;

}
