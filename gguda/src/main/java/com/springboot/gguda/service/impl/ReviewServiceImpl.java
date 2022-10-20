package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.data.dto.QuestionResponseDto;
import com.springboot.gguda.data.dto.ReviewDto;
import com.springboot.gguda.data.dto.ReviewResponseDto;
import com.springboot.gguda.data.entity.ImageFile;
import com.springboot.gguda.data.entity.Product;
import com.springboot.gguda.data.entity.Question;
import com.springboot.gguda.data.entity.Review;
import com.springboot.gguda.data.repository.MemberRepository;
import com.springboot.gguda.data.repository.ProductRepository;
import com.springboot.gguda.data.repository.ReviewRepository;
import com.springboot.gguda.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public ReviewResponseDto saveReviewDto(ReviewDto reviewDto, MultipartFile file) throws IOException {
        Review review = new Review();
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        review.setProduct(productRepository.findById(reviewDto.getProductId()).get());
        review.setMember(memberRepository.findById(reviewDto.getMemberId()).get());


        if (file==null) {
            review.setFilename(null);
            review.setFilepath(null);
        } else {

                // 파일 저장
                String projectPath = System.getProperty("user.dir") + "\\gguda\\src\\main\\resources\\static\\files";
                UUID uuid = UUID.randomUUID();
                String fileName = uuid + "_" + file.getOriginalFilename();
                File saveFile = new File(projectPath, fileName);
                file.transferTo(saveFile);

                review.setFilename(fileName);
                review.setFilepath("/files/" + fileName);

        }


        reviewRepository.save(review);

        ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
        reviewResponseDto.setId(review.getId());
        reviewResponseDto.setContent(review.getContent());
        reviewResponseDto.setStars(review.getStars());
        reviewResponseDto.setCreatedAt(review.getCreatedAt());
        reviewResponseDto.setUpdatedAt(review.getUpdatedAt());
        reviewResponseDto.setProductId(review.getProduct().getId());
        reviewResponseDto.setMemberId(review.getMember().getId());
        reviewResponseDto.setFilename(review.getFilename());
        reviewResponseDto.setFilepath(review.getFilepath());

        return reviewResponseDto;
    }

    @Override
    public List<ReviewResponseDto> getReviewPage(String content, int page) {
        Page<Review> reviewPage = reviewRepository.findAllByContentContaining(content, PageRequest.of(page-1,12, Sort.by(Sort.Direction.DESC, "CreatedAt")));
        // 생성순 DESC로 정렬되어 페이징처리    // pageable의 구현체 = PageRequest

        List<Review> reviews = reviewPage.getContent();
        List<ReviewResponseDto> reviewResponseDtoList = new ArrayList<>();

        for(Review review : reviews) {
            ReviewResponseDto dto = ReviewResponseDto.builder()
                    .id(review.getId())
                    .content(review.getContent())
                    .stars(review.getStars())
                    .productId(review.getProduct().getId())
                    .memberId(review.getMember().getId())
                    .createdAt(review.getCreatedAt())
                    .updatedAt(review.getUpdatedAt())
                    .filename(review.getFilename())
                    .filepath(review.getFilepath())
                    .build();

            reviewResponseDtoList.add(dto);
        }
        return reviewResponseDtoList;
    }

    @Override
    public List<ReviewResponseDto> getReviewList(Long id) {

        List<Review> reviews = reviewRepository.findAllByMemberIdOrderByCreatedAtDesc(id);

        List<ReviewResponseDto> reviewResponseDtoList = new ArrayList<>();

        for(Review review : reviews){
            ReviewResponseDto dto = ReviewResponseDto.builder()
                    .id(review.getId())
                    .content(review.getContent())
                    .stars(review.getStars())
                    .productId(review.getProduct().getId())
                    .memberId(review.getMember().getId())
                    .createdAt(review.getCreatedAt())
                    .updatedAt(review.getUpdatedAt())
                    .filename(review.getFilename())
                    .filepath(review.getFilepath())
                    .build();

            reviewResponseDtoList.add(dto);
        }

        return reviewResponseDtoList;
    }

    @Override
    public ReviewResponseDto getReview(Long id) {
        Review review = reviewRepository.getById(id);

        ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
        reviewResponseDto.setId(review.getId());
        reviewResponseDto.setContent(review.getContent());
        reviewResponseDto.setStars(review.getStars());
        reviewResponseDto.setCreatedAt(review.getCreatedAt());
        reviewResponseDto.setUpdatedAt(review.getUpdatedAt());
        reviewResponseDto.setProductId(review.getProduct().getId());
        reviewResponseDto.setMemberId(review.getMember().getId());
        reviewResponseDto.setFilepath(review.getFilepath());
        reviewResponseDto.setFilename(review.getFilename());

        return reviewResponseDto;
    }

    @Override
    public List<ReviewResponseDto> getAllReview() {
        List<Review> reviews = reviewRepository.findAllByOrderByCreatedAtDesc();

        List<ReviewResponseDto> reviewResponseDtoList = new ArrayList<>();

        for(Review review : reviews){
            ReviewResponseDto dto = ReviewResponseDto.builder()
                    .id(review.getId())
                    .content(review.getContent())
                    .stars(review.getStars())
                    .productId(review.getProduct().getId())
                    .memberId(review.getMember().getId())
                    .createdAt(review.getCreatedAt())
                    .updatedAt(review.getUpdatedAt())
                    .filename(review.getFilename())
                    .filepath(review.getFilepath())
                    .build();

            reviewResponseDtoList.add(dto);
        }

        return reviewResponseDtoList;
    }

    @Override
    public ReviewResponseDto putReview(ReviewDto reviewDto, Long id) {
        Review review = reviewRepository.getById(id);

        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());

        reviewRepository.save(review);

        ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
        reviewResponseDto.setId(review.getId());
        reviewResponseDto.setContent(review.getContent());
        reviewResponseDto.setStars(review.getStars());
        reviewResponseDto.setProductId(review.getProduct().getId());
        reviewResponseDto.setMemberId(review.getMember().getId());
        reviewResponseDto.setCreatedAt(review.getCreatedAt());
        reviewResponseDto.setUpdatedAt(review.getUpdatedAt());
        reviewResponseDto.setFilename(review.getFilename());
        reviewResponseDto.setFilepath(review.getFilepath());

        return reviewResponseDto;
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }


}
