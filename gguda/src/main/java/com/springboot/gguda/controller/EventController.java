package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.*;
import com.springboot.gguda.data.entity.EventProduct;
import com.springboot.gguda.result.DetailResult;
import com.springboot.gguda.result.EventDetailResult;
import com.springboot.gguda.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/event")
public class EventController {

    private final EventProductService eventProductService;
    private final EventReviewService eventReviewService;
    private final EventQuestionService eventQuestionService;

    @Autowired
    public EventController(EventProductService eventProductService,
                           EventReviewService eventReviewService,
                           EventQuestionService eventQuestionService) {
        this.eventProductService = eventProductService;
        this.eventReviewService = eventReviewService;
        this.eventQuestionService = eventQuestionService;
    }

    @GetMapping(value = "/show") // 행사용품 페이징처리해서 가져오기
    public List<EventProductResponseDto> getEventProductList(String sort, int page) {

        if (sort == null) {
            List<EventProductResponseDto> eventProducts = eventProductService.getEventProductPageAll(page);
            return eventProducts;
        } else {
            List<EventProductResponseDto> eventProducts = eventProductService.getEventProductPage(sort, page);
            return eventProducts;
        }
    }

    @GetMapping(value = "/detail") // 행사용품 detail 페이지 가져오기 (상품에딸린 후기,질문글포함)
    public EventDetailResult getEventProduct(Long id) {
        EventProductResponseDto eventProductResponseDto = eventProductService.getEventProduct(id);
        // 행사용품정보 가져오기
        List<EventQuestionResponseDto> eventQuestionResponseDtos = eventProductService.getEventQuestion(id);
        // 행사용품에 해당하는 질문리스트 가져오기
        List<EventReviewResponseDto> eventReviewResponseDtos = eventProductService.getEventReview(id);
        // 행사용품에 해당하는 후기리스트 가져오기

        return new EventDetailResult(eventProductResponseDto, eventQuestionResponseDtos, eventReviewResponseDtos);
    }

    //          행사용품 후기글 return API
    @GetMapping(value = "/review") // 행사용품 후기글 페이징처리해서 가져오기
    public List<EventReviewResponseDto> getEventReview(String content, int page) {

        if (content == null) {
            content = "";
        }

        List<EventReviewResponseDto> eventReviews = eventReviewService.getEventReviewPage(content, page); // ReviewResponseDto로 반환됨

        return eventReviews;
    }

    @PostMapping(value = "register/review") // 행사용품 후기 등록하기
    public ResponseEntity<EventReviewResponseDto> createEventReview(@RequestBody EventReviewDto eventReviewDto) {
        EventReviewResponseDto eventReviewResponseDto = eventReviewService.saveEventReviewDto(eventReviewDto);

        return ResponseEntity.status(HttpStatus.OK).body(eventReviewResponseDto);
    }

    @PostMapping(value = "register/question") // 행사용품 질문등록하기//////////////////////////////////////////////////////////
    public ResponseEntity<EventQuestionResponseDto> createEventQuestion(@RequestBody EventQuestionDto eventQuestionDto) {
        EventQuestionResponseDto eventQuestionResponseDto = eventQuestionService.saveEventQuestionDto(eventQuestionDto);

        return ResponseEntity.status(HttpStatus.OK).body(eventQuestionResponseDto);
    }

    @PostMapping(value = "register/event-product") // 행사상품등록하기
    public ResponseEntity<EventProductResponseDto> createEventProduct(@RequestBody EventProductDto eventProductDto) {
        EventProductResponseDto eventProductResponseDto = eventProductService.saveEventProductDto(eventProductDto);

        return ResponseEntity.status(HttpStatus.OK).body(eventProductResponseDto);
    }



}
