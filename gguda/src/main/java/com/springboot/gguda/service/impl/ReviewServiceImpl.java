package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.QuestionDto;
import com.springboot.gguda.data.dto.QuestionResponseDto;
import com.springboot.gguda.data.dto.ReviewDto;
import com.springboot.gguda.data.dto.ReviewResponseDto;
import com.springboot.gguda.data.entity.Question;
import com.springboot.gguda.data.entity.Review;
import com.springboot.gguda.data.repository.MemberRepository;
import com.springboot.gguda.data.repository.ProductRepository;
import com.springboot.gguda.data.repository.QuestionRepository;
import com.springboot.gguda.data.repository.ReviewRepository;
import com.springboot.gguda.service.QuestionService;
import com.springboot.gguda.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductRepository productRepository, MemberRepository memberRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public ReviewResponseDto saveReviewDto(ReviewDto reviewDto) {
        Review review = new Review();
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        review.setRegDate(reviewDto.getRegDate());
        review.setProduct(productRepository.findById(reviewDto.getProductId()).get());
        review.setMember(memberRepository.findById(reviewDto.getMemberId()).get());

        reviewRepository.save(review);

        ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
        reviewResponseDto.setId(review.getId());
        reviewResponseDto.setContent(review.getContent());
        reviewResponseDto.setStars(review.getStars());
        reviewResponseDto.setRegDate(review.getRegDate());
        reviewResponseDto.setProductId(review.getProduct().getId());
        reviewResponseDto.setMemberId(review.getMember().getId());

        return reviewResponseDto;
    }
}
