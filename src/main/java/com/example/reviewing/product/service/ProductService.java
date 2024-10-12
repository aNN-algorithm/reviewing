package com.example.reviewing.product.service;

import com.example.reviewing.product.repository.JpaProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final JpaProductRepository jpaProductRepository;

    public void updateIncrementProductReview(Long productId, int score) {

        jpaProductRepository.updateIncrementProductReviewById(productId, score);
    }
}
