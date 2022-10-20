package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.ReviewDto;
import com.springboot.gguda.data.dto.ReviewResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReviewService {

    ReviewResponseDto saveReviewDto(ReviewDto reviewDto, MultipartFile file) throws IOException;


    List<ReviewResponseDto> getReviewPage(String content, int page);

    List<ReviewResponseDto> getReviewList(Long id);

    ReviewResponseDto getReview(Long id);

    List<ReviewResponseDto> getAllReview();

    ReviewResponseDto putReview(ReviewDto reviewDto, Long id);

    void deleteReview(Long id);
}
