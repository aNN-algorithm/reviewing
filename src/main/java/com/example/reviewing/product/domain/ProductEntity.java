package com.example.reviewing.product.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "review_count", nullable = false)
    private Long reviewCount;

    @Column(name = "score", nullable = false)
    private Double score;

}
