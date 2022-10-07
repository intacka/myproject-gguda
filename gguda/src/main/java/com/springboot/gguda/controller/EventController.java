package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.*;
import com.springboot.gguda.data.entity.EventProduct;
import com.springboot.gguda.result.DetailResult;
import com.springboot.gguda.result.EventDetailResult;
import com.springboot.gguda.result.EventQAResult;
import com.springboot.gguda.result.QAResult;
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
    private final EventQuestionAnswerService eventQuestionAnswerService;

    @Autowired
    public EventController(EventProductService eventProductService,
                           EventReviewService eventReviewService,
                           EventQuestionService eventQuestionService,
                           EventQuestionAnswerService eventQuestionAnswerService) {
        this.eventProductService = eventProductService;
        this.eventReviewService = eventReviewService;
        this.eventQuestionService = eventQuestionService;
        this.eventQuestionAnswerService = eventQuestionAnswerService;
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
        List<EventQuestionAnswerResponseDto> eventQuestionAnswerResponseDtos = eventProductService.getEventQuestionAnswer(id);
        // 상품에 해당하는 질문리스트, 답변리스트 가져오기
        List<EventReviewResponseDto> eventReviewResponseDtos = eventProductService.getEventReview(id);
        // 행사용품에 해당하는 후기리스트 가져오기

        return new EventDetailResult(eventProductResponseDto, eventQuestionResponseDtos, eventQuestionAnswerResponseDtos, eventReviewResponseDtos);
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

    @GetMapping(value = "/event-review/detail")   // 행사용품 후기글 Detail 가져오기
    public EventReviewResponseDto getEventReviewDetail(Long id){
        EventReviewResponseDto eventReview = eventReviewService.getEventReview(id);

        return eventReview;
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

//------------------------------------------------------------------------------------------------------------------------------------------

    @PutMapping(value = "/put/event-product") //              행사용품상품수정하기
    public ResponseEntity<EventProductResponseDto> putEventProduct(@RequestBody EventProductDto eventProductDto, Long id) {
        EventProductResponseDto eventProductResponseDto = eventProductService.putEventProduct(eventProductDto, id);

        return ResponseEntity.status(HttpStatus.OK).body(eventProductResponseDto);
    }

    @PostMapping(value = "/delete/event-product")         //  행사용품상품삭제하기
    public EventProductResponseDto dropEventProduct(Long id) {

        EventProductResponseDto eventProductResponseDto = eventProductService.deleteEventProduct(id);

        return eventProductResponseDto;
    }

    @PutMapping(value = "/put/event-review") //           행사용품 후기 수정하기
    public ResponseEntity<EventReviewResponseDto> putEventReview(@RequestBody EventReviewDto eventReviewDto, Long id) {
        EventReviewResponseDto eventReviewResponseDto = eventReviewService.putEventReview(eventReviewDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(eventReviewResponseDto);
    }

    @DeleteMapping(value = "/delete/event-review")//      행사용품 후기 삭제하기
    public boolean dropEventReview(Long id) {
        eventReviewService.deleteEventReview(id);
        return true;
    }

    @GetMapping(value = "/event-review/all")   //        행사용품 후기글 모든목록 가져오기 (관리자용)
    public List<EventReviewResponseDto> getAllEventReview(){
        List<EventReviewResponseDto> eventReviews = eventReviewService.getAllEventReview();

        return eventReviews;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 답변글 등록
    @PostMapping(value = "register/event-question-answer") // 답변등록하기//////////////////////////////////////////////////////////
    public ResponseEntity<EventQuestionAnswerResponseDto> createEventQuestionAnswer(@RequestBody EventQuestionAnswerDto eventQuestionAnswerDto) {
        EventQuestionAnswerResponseDto eventQuestionAnswerResponseDto = eventQuestionAnswerService.saveEventQuestionAnswerDto(eventQuestionAnswerDto);

        return ResponseEntity.status(HttpStatus.OK).body(eventQuestionAnswerResponseDto);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 모든질문답변조회 (관리자용)
    @GetMapping(value = "question-answer/view") // 모든 질문,답변 가져오기(관리자용)
    public EventQAResult getQA() {
        List<EventQuestionResponseDto> eventQuestionResponseDtos = eventQuestionService.getAllEventQuestion();
        List<EventQuestionAnswerResponseDto> eventQuestionAnswerResponseDtos = eventQuestionAnswerService.getAllEventQuestionAnswer();
        // 모든 질문, 답변 리스트로 쫙 가져오기

        return new EventQAResult(eventQuestionResponseDtos, eventQuestionAnswerResponseDtos);
    }

    // 답변수정
    @PutMapping(value = "/put/answer") //            답변 수정하기
    public ResponseEntity<EventQuestionAnswerResponseDto> putEventQuestionAnswer(@RequestBody EventQuestionAnswerDto eventQuestionAnswerDto, Long id) {
        EventQuestionAnswerResponseDto eventQuestionAnswerResponseDto = eventQuestionAnswerService.putEventQuestionAnswer(eventQuestionAnswerDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(eventQuestionAnswerResponseDto);
    }

    @DeleteMapping(value = "/delete/answer")//       답변 삭제하기
    public boolean dropEventQuestionAnswer(Long id) {
        eventQuestionAnswerService.deleteEventQuestionAnswer(id);
        return true;
    }





}
