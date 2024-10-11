package com.example.reviewing.product.controller;

import com.example.reviewing.product.domain.ProductEntity;
import com.example.reviewing.product.domain.dto.ReviewSaveRequest;
import com.example.reviewing.product.domain.dto.ReviewSaveResponse;
import com.example.reviewing.product.service.ProductService;
import com.example.reviewing.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ReviewService reviewService;

    @PostMapping("/products/{productId}/reviews")
    public void saveReview(@PathVariable Long productId,
                           @RequestBody ReviewSaveRequest reviewSaveRequest) {
        reviewService.saveReview(productId, reviewSaveRequest);
    }
}
