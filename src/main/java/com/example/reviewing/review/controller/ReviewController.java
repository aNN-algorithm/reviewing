package com.example.reviewing.review.controller;

import com.example.reviewing.product.domain.dto.ReviewSaveRequest;
import com.example.reviewing.review.domain.dto.ReviewResponse;
import com.example.reviewing.review.service.FileService;
import com.example.reviewing.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final FileService fileService;
    
    @PostMapping("/products/{productId}/reviews")
    public void saveReview(@PathVariable Long productId,
                           @RequestBody ReviewSaveRequest reviewSaveRequest) throws IOException {
        reviewService.saveReview(productId, reviewSaveRequest);
    }

    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<ReviewResponse> getReview(@PathVariable Long productId,
                                    @RequestParam("cursor") Long reviewId,
                                    @RequestParam("size") Optional<Integer> size) {

        int pageSize = size.orElse(10);

        return ResponseEntity.ok(reviewService.getReview(productId, reviewId, pageSize));
    }
}
