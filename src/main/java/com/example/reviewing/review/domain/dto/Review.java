package com.example.reviewing.review.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    Long id;
    Long userId;
    int score;
    String content;
    String imageUrl;
    LocalDateTime createdAt;
}
