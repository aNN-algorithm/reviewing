package com.example.reviewing.review.repository;

import com.example.reviewing.review.domain.ReviewEntity;
import com.example.reviewing.review.domain.dto.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaReviewRepository extends JpaRepository<ReviewEntity, Long> {

    boolean existsByProductIdAndUserId(Long productId, Long userId);

    List<ReviewEntity> findAllByProductIdOrderByIdDesc(Long productId, Pageable page);
//    List<Review> findByIdLessThanAndProductIdOrderByDesc(Long id, Long productId, Pageable page);
    List<ReviewEntity> findByIdLessThanAndProductIdOrderByIdDesc(Long id, Long productId, Pageable page);
}
