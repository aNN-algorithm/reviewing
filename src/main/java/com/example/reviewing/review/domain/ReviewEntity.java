package com.example.reviewing.review.domain;

import com.example.reviewing.product.domain.dto.ReviewSaveRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "name_id", nullable = false)
    private Long userId;

    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public static ReviewEntity toEntity(Long productId, ReviewSaveRequest request) {
        return ReviewEntity.builder()
                .productId(productId)
                .userId(request.getUserId())
                .score(request.getScore())
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
