package com.example.reviewing.product.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ReviewSaveRequest {

    Long id;

    @NotBlank(message = "사용자 ID를 입력해주세요.")
    Long userId;

    @Size(min = 1, max = 5, message = "1 ~ 5점 사이 값을 입력해주세요.")
    int score;

    String content;
}
