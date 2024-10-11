package com.example.reviewing.review.controller;

import com.example.reviewing.review.domain.dto.ReviewResponse;
import com.example.reviewing.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<ReviewResponse> getReview(@PathVariable Long productId,
                                    @RequestParam("cursor") Long reviewId,
                                    @RequestParam("size") Optional<Integer> size) {

        int pageSize = size.orElse(10);

        return ResponseEntity.ok(reviewService.getReview(productId, reviewId, pageSize));
    }
}
