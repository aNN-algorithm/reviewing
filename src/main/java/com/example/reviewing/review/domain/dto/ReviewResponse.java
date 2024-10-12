package com.example.reviewing.review.domain.dto;

import com.example.reviewing.review.domain.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {

    Long totalCount;
    double score;
    Long cursor;
    List<ReviewEntity> reviews;
}
