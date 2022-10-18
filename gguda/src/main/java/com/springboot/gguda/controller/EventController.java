package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.*;
import com.springboot.gguda.data.dto.apply.EstimateEventDto;
import com.springboot.gguda.data.dto.apply.EstimateEventResponseDto;
import com.springboot.gguda.data.entity.EventProduct;
import com.springboot.gguda.result.*;
import com.springboot.gguda.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/event")
public class EventController {

    private final EventProductService eventProductService;
    private final EventReviewService eventReviewService;
    private final EventQuestionService eventQuestionService;
    private final EventQuestionAnswerService eventQuestionAnswerService;
    private final EventBasketService eventBasketService;
    private final EstimateEventService estimateEventService;

    @Autowired
    public EventController(EventProductService eventProductService,
                           EventReviewService eventReviewService,
                           EventQuestionService eventQuestionService,
                           EventQuestionAnswerService eventQuestionAnswerService,
                           EventBasketService eventBasketService,
                           EstimateEventService estimateEventService) {
        this.eventProductService = eventProductService;
        this.eventReviewService = eventReviewService;
        this.eventQuestionService = eventQuestionService;
        this.eventQuestionAnswerService = eventQuestionAnswerService;
        this.eventBasketService = eventBasketService;
        this.estimateEventService = estimateEventService;
    }

    // 행사상품등록하기
    @PostMapping(value = "/product/register")
    public ResponseEntity<EventProductResponseDto> createEventProduct(
                    EventProductDto eventProductDto,
                    @RequestPart(name = "images", required=false) List<MultipartFile> files) throws IOException {
        EventProductResponseDto eventProductResponseDto = eventProductService.saveEventProductDto(eventProductDto, files);

        return ResponseEntity.status(HttpStatus.OK).body(eventProductResponseDto);
    }

    // 행사용품상품수정하기
    @PutMapping(value = "/product/put")
    public ResponseEntity<EventProductResponseDto> putEventProduct(@RequestBody EventProductDto eventProductDto, Long id) {
        EventProductResponseDto eventProductResponseDto = eventProductService.putEventProduct(eventProductDto, id);

        return ResponseEntity.status(HttpStatus.OK).body(eventProductResponseDto);
    }

    // 행사용품상품삭제하기
    @PostMapping(value = "/product/delete")
    public EventProductResponseDto dropEventProduct(Long id) {

        EventProductResponseDto eventProductResponseDto = eventProductService.deleteEventProduct(id);

        return eventProductResponseDto;
    }



    // 행사용품 페이징처리해서 가져오기
    @GetMapping(value = "/product-list/show")
    public List<EventProductResponseDto> getEventProductList(String sort, int page) {

        if (sort == null) {
            List<EventProductResponseDto> eventProducts = eventProductService.getEventProductPageAll(page);
            return eventProducts;
        } else {
            List<EventProductResponseDto> eventProducts = eventProductService.getEventProductPage(sort, page);
            return eventProducts;
        }
    }

    // 행사용품 detail 페이지 가져오기 (상품에딸린 후기,질문글포함)
    @GetMapping(value = "/product/view-detail")
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




    // 행사용품 후기글 return API
    @GetMapping(value = "/review-list/view") // 행사용품 후기글 페이징처리해서 가져오기
    public List<EventReviewResponseDto> getEventReview(String content, int page) {

        if (content == null) {
            content = "";
        }

        List<EventReviewResponseDto> eventReviews = eventReviewService.getEventReviewPage(content, page); // ReviewResponseDto로 반환됨

        return eventReviews;
    }

    @GetMapping(value = "/review/view-detail")   // 행사용품 후기글 Detail 가져오기
    public EventReviewResponseDto getEventReviewDetail(Long id){
        EventReviewResponseDto eventReview = eventReviewService.getEventReview(id);

        return eventReview;
    }

    @PostMapping(value = "review/register") // 행사용품 후기 등록하기
    public ResponseEntity<EventReviewResponseDto> createEventReview(
            EventReviewDto eventReviewDto,
            @RequestPart(name = "images", required = false) List<MultipartFile> files) throws IOException {
        EventReviewResponseDto eventReviewResponseDto = eventReviewService.saveEventReviewDto(eventReviewDto, files);

        return ResponseEntity.status(HttpStatus.OK).body(eventReviewResponseDto);
    }

    @PutMapping(value = "/review/put") //           행사용품 후기 수정하기
    public ResponseEntity<EventReviewResponseDto> putEventReview(@RequestBody EventReviewDto eventReviewDto, Long id) {
        EventReviewResponseDto eventReviewResponseDto = eventReviewService.putEventReview(eventReviewDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(eventReviewResponseDto);
    }

    @DeleteMapping(value = "/review/delete")//      행사용품 후기 삭제하기
    public boolean dropEventReview(Long id) {
        eventReviewService.deleteEventReview(id);
        return true;
    }

    @GetMapping(value = "/review-list/view-all")   //        행사용품 후기글 모든목록 가져오기 (관리자용)
    public List<EventReviewResponseDto> getAllEventReview(){
        List<EventReviewResponseDto> eventReviews = eventReviewService.getAllEventReview();

        return eventReviews;
    }







//------------------------------------------------------------------------------------------------------------------------------------------


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 행사용품 질문등록하기
    @PostMapping(value = "question/register") //////////////////////////////////////////////////////////
    public ResponseEntity<EventQuestionResponseDto> createEventQuestion(@RequestBody EventQuestionDto eventQuestionDto) {
        EventQuestionResponseDto eventQuestionResponseDto = eventQuestionService.saveEventQuestionDto(eventQuestionDto);

        return ResponseEntity.status(HttpStatus.OK).body(eventQuestionResponseDto);
    }


    // 답변글 등록
    @PostMapping(value = "answer/register") // 답변등록하기//////////////////////////////////////////////////////////
    public ResponseEntity<EventQuestionAnswerResponseDto> createEventQuestionAnswer(@RequestBody EventQuestionAnswerDto eventQuestionAnswerDto) {
        EventQuestionAnswerResponseDto eventQuestionAnswerResponseDto = eventQuestionAnswerService.saveEventQuestionAnswerDto(eventQuestionAnswerDto);

        return ResponseEntity.status(HttpStatus.OK).body(eventQuestionAnswerResponseDto);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 답변수정
    @PutMapping(value = "/answer/put") //            답변 수정하기
    public ResponseEntity<EventQuestionAnswerResponseDto> putEventQuestionAnswer(@RequestBody EventQuestionAnswerDto eventQuestionAnswerDto, Long id) {
        EventQuestionAnswerResponseDto eventQuestionAnswerResponseDto = eventQuestionAnswerService.putEventQuestionAnswer(eventQuestionAnswerDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(eventQuestionAnswerResponseDto);
    }

    @DeleteMapping(value = "/answer/delete")//       답변 삭제하기
    public boolean dropEventQuestionAnswer(Long id) {
        eventQuestionAnswerService.deleteEventQuestionAnswer(id);
        return true;
    }

    // 모든질문답변조회 (관리자용)
    @GetMapping(value = "question-answer/view-all") // 모든 질문,답변 가져오기(관리자용)
    public EventQAResult getQA() {
        List<EventQuestionResponseDto> eventQuestionResponseDtos = eventQuestionService.getAllEventQuestion();
        List<EventQuestionAnswerResponseDto> eventQuestionAnswerResponseDtos = eventQuestionAnswerService.getAllEventQuestionAnswer();
        // 모든 질문, 답변 리스트로 쫙 가져오기

        return new EventQAResult(eventQuestionResponseDtos, eventQuestionAnswerResponseDtos);
    }

    //////////////////////////////////////////////////

    @PostMapping(value = "/basket/add")                      // 행사바구니에 행사용품 추가하기
    public ResponseEntity<EventBasketResponseDto> addEventProductInEventBasket(Long eventProductId, Long memberId, Long amount) {
        EventBasketResponseDto eventBasketResponseDto = eventBasketService.addInEventBasket(eventProductId, memberId, amount);

        return ResponseEntity.status(HttpStatus.OK).body(eventBasketResponseDto);
    }

    @GetMapping(value = "/basket-list/view")               //   행사바구니에 담긴 행사용품 List 나타내기
    public List<EventBasketResult> getEventBasketProductList(Long memberId) {
        List<EventBasketResult> eventBasketResultList = eventBasketService.getEventBasketEventProductList(memberId);

        return eventBasketResultList;
    }

    @PutMapping(value="/basket/put")                // 행사바구니 수량수정
    public ResponseEntity<EventBasketResponseDto> putEventBasket(Long eventProductId, Long memberId, Long amount) {
        EventBasketResponseDto eventBasketResponseDto = eventBasketService.putEventBasket(eventProductId, memberId, amount);

        return ResponseEntity.status(HttpStatus.OK).body(eventBasketResponseDto);
    }

    @DeleteMapping(value = "/basket/delete")        // 행사바구니 목록 삭제 (행사바구니id줘야함)
    public boolean deleteEventBasketEventProduct(Long id) {
        boolean result = eventBasketService.deleteEventBasketEventProduct(id);
        return result;
    }


    //          견적신청-행사용품신청 API -> 관리자DB로 넘어간다 ***************************
    @PostMapping(value = "/event/apply")
    public ResponseEntity<EstimateEventResponseDto> applyEvent(@RequestBody EstimateEventDto estimateEventDto) {
        EstimateEventResponseDto estimateEventResponseDto = estimateEventService.saveEstimateEventDto(estimateEventDto);

        return ResponseEntity.status(HttpStatus.OK).body(estimateEventResponseDto);
    }


    // 견적신청List확인(행사용품)

    @GetMapping(value = "/estimate-list/view")
    public List<EstimateEventResponseDto> getAllEstimateEvent() {
        List<EstimateEventResponseDto> all = estimateEventService.getAllEstimateEvent();

        return all;
    }

    // 견적신청디테일보기 (행사용품)

    @GetMapping(value = "estimate/view-detail")
    public EstimateEventResponseDto getEstimateEvent(Long id) {
        EstimateEventResponseDto estimateEventResponseDto = estimateEventService.getEstimateEvent(id);

        return estimateEventResponseDto;
    }

    // 견적신청허가 (행사용품)
    @PutMapping(value = "/estimate/approval")
    public EstimateEventResponseDto putEstimateEvent(Long id) {
        EstimateEventResponseDto estimateEventResponseDto = estimateEventService.putEstimateEvent(id);

        return estimateEventResponseDto;
    }


}
