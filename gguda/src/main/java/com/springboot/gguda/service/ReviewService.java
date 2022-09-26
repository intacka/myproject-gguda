package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.QuestionDto;
import com.springboot.gguda.data.dto.QuestionResponseDto;
import com.springboot.gguda.data.dto.ReviewDto;
import com.springboot.gguda.data.dto.ReviewResponseDto;

public interface ReviewService {

    ReviewResponseDto saveReviewDto(ReviewDto reviewDto);

}
