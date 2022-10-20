package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.ReviewDto;
import com.springboot.gguda.data.dto.ReviewResponseDto;
import com.springboot.gguda.data.repository.CouponRepository;
import com.springboot.gguda.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {


    private final ReviewService reviewService;
    private final ReserveHistoryService reserveHistoryService;


    @Autowired
    public ReviewController(ReviewService reviewService,
                            ReserveHistoryService reserveHistoryService) {
        this.reviewService = reviewService;
        this.reserveHistoryService = reserveHistoryService;
    }

    @PostMapping(value = "/register") //       후기 등록하기
    public ResponseEntity<ReviewResponseDto> createReview(
            ReviewDto reviewDto,
            MultipartFile file) throws IOException {
        ReviewResponseDto reviewResponseDto = reviewService.saveReviewDto(reviewDto, file);
        reserveHistoryService.createReviewReserveHistory(reviewResponseDto.getMemberId());      // 후기쓰면 50원 적립, 내역도 추가

        return ResponseEntity.status(HttpStatus.OK).body(reviewResponseDto);
    }

    @PutMapping(value = "/put") //            후기 수정하기
    public ResponseEntity<ReviewResponseDto> putReview(@RequestBody ReviewDto reviewDto, Long id) {
        ReviewResponseDto reviewResponseDto = reviewService.putReview(reviewDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(reviewResponseDto);
    }

    @DeleteMapping(value = "/delete")//       후기 삭제하기
    public boolean dropReview(Long id) {
        reviewService.deleteReview(id);
        return true;
    }

    @GetMapping(value = "/view-all")   //         후기글 모든목록 가져오기 (관리자용)
    public List<ReviewResponseDto> getAllReview(){
        List<ReviewResponseDto> reviews = reviewService.getAllReview();

        return reviews;
    }

    //          후기글 return API
    @GetMapping(value = "/view-paging") // 후기글 페이징처리해서 가져오기
    public List<ReviewResponseDto> getReview(String content, int page) {

        if (content == null) {
            content = "";
        }

        List<ReviewResponseDto> reviews = reviewService.getReviewPage(content, page); // ReviewResponseDto로 반환됨

        return reviews;
    }

    // 후기글 Detail 가져오기
    @GetMapping(value = "/view-detail")
    public ReviewResponseDto getReviewDetail(Long id){
        ReviewResponseDto review = reviewService.getReview(id);

        return review;
    }





}
