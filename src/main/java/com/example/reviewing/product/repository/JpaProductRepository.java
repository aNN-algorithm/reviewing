package com.example.reviewing.product.repository;

import com.example.reviewing.product.domain.ProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

    @Transactional
    @Modifying
    @Query("update ProductEntity p set p.reviewCount = p.reviewCount + 1, p.score = p.score + :score where p.id = :productId")
    void updateIncrementProductReviewById(Long productId, Integer score);
}
