package com.example.reviewing.review.service;

import com.example.reviewing.product.domain.ProductEntity;
import com.example.reviewing.product.domain.dto.ReviewSaveRequest;
import com.example.reviewing.product.repository.JpaProductRepository;
import com.example.reviewing.product.service.ProductService;
import com.example.reviewing.review.domain.ReviewEntity;
import com.example.reviewing.review.domain.dto.ReviewResponse;
import com.example.reviewing.review.repository.JpaReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final JpaProductRepository jpaProductRepository;
    private final JpaReviewRepository jpaReviewRepository;
    private final ProductService productService;

    final static int DEFAULT_PAGE_SIZE = 10;

    public void saveReview(Long productId, ReviewSaveRequest request) {

        // 리뷰는 존재하는 상품에만 적용
        validate(jpaProductRepository.existsById(productId), "해당 상품은 존재하지 않습니다.");

        // 유저는 하나의 상품에 대해 하나의 리뷰만 작성 가능
        validate(!jpaReviewRepository.existsByProductIdAndUserId(productId, request.getUserId()),
                "해당 상품에 이미 리뷰를 작성하였습니다.");

        // 점수는 1 ~ 5점 값
        validate(request.getScore() > 0 && request.getScore() <= 5, "1 ~ 5점 사이 값을 입력해주세요.");

        // 저장
//        jpaReviewRepository.save(ReviewEntity.toEntity(productId, request));
        jpaReviewRepository.save(ReviewEntity.toEntity(productId, request));

        // product 에 반영
        productService.updateIncrementProductReview(productId, request.getScore());
    }

    public ReviewResponse getReview(Long productId, Long lastReviewId, int size) {

        ProductEntity productEntity = jpaProductRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("not found ID"));
        Pageable page = PageRequest.of(0, size);
        List<ReviewEntity> reviews = findAllByLastReviewIdCheckExistCursor(productId, lastReviewId, page);

        return ReviewResponse.builder()
                .totalCount(productEntity.getReviewCount())
                .score(productEntity.getScore() / productEntity.getReviewCount())
                .cursor(reviews.get(reviews.size() - 1).getId())
                .reviews(reviews)
                .build();
    }

    public List<ReviewEntity> findAllByLastReviewIdCheckExistCursor(Long productId, Long lastReviewId, Pageable page) {

        return lastReviewId == 0 ? jpaReviewRepository.findAllByProductIdOrderByIdDesc(productId, page)
                : jpaReviewRepository.findByIdLessThanAndProductIdOrderByIdDesc(lastReviewId, productId, page);
    }


    public void validate(boolean isValid, String message) {
        if (!isValid) {
            throw new IllegalArgumentException(message);
        }
    }
}
