package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.ReviewDto;
import com.springboot.gguda.data.dto.ReviewResponseDto;

import java.util.List;

public interface ReviewService {

    ReviewResponseDto saveReviewDto(ReviewDto reviewDto);


    List<ReviewResponseDto> getReviewPage(String content, int page);

    List<ReviewResponseDto> getReviewList(Long id);
}
