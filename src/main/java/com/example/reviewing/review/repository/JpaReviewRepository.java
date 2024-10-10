package com.example.reviewing.review.repository;

import com.example.reviewing.review.domain.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaReviewRepository extends JpaRepository<ReviewEntity, Long> {

    boolean existsByProductIdAndUserId(Long productId, Long userId);
}
