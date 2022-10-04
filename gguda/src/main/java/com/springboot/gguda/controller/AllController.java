package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.*;
import com.springboot.gguda.data.dto.apply.*;
import com.springboot.gguda.data.entity.apply.ApplymentPartners;
import com.springboot.gguda.data.entity.apply.EstimateElec;
import com.springboot.gguda.result.DetailResult;
import com.springboot.gguda.result.MainResult;
import com.springboot.gguda.service.*;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AllController {

    private final ProductService productService;
    private final QuestionService questionService;
    private final MemberService memberService;
    private final ReviewService reviewService;
    private final EstimateElecService estimateElecService;
    private final EstimateTVService estimateTVService;
    private final EstimateEventService estimateEventService;
    private final ApplymentPartnersService applymentPartnersService;

    @Autowired
    public AllController(ProductService productService,
                         QuestionService questionService,
                         MemberService memberService,
                         ReviewService reviewService,
                         EstimateElecService estimateElecService,
                         EstimateTVService estimateTVService,
                         ApplymentPartnersService applymentPartnersService,
                         EstimateEventService estimateEventService) {
        this.productService = productService;
        this.questionService = questionService;
        this.memberService = memberService;
        this.reviewService = reviewService;
        this.estimateElecService = estimateElecService;
        this.estimateTVService = estimateTVService;
        this.applymentPartnersService = applymentPartnersService;
        this.estimateEventService = estimateEventService;
    }


    //   메인페이지 API
    @GetMapping(value = "/main")    // 메인페이지 API
    public MainResult getMainPage(){
        List<ProductResponseDto> popular = productService.getPopularTop10Product();
        List<ProductResponseDto> macbook10 = productService.getTop10Product("맥북");
        List<ProductResponseDto> ipad10 = productService.getTop10Product("아이패드");
        List<ProductResponseDto> samsungNotebook10 = productService.getTop10Product("노트북", "삼성전자");
        List<ProductResponseDto> samsungPc = productService.getTop10Product("PC", "삼성전자");
        List<ProductResponseDto> BenQMonitor = productService.getTop10Product("모니터", "벤큐");

        return new MainResult(popular, macbook10, ipad10, samsungNotebook10, samsungPc, BenQMonitor);
    }

    @GetMapping(value = "/product/detail") // 디테일페이지에 해당하는 상품정보 불러오기
    public DetailResult getProduct(Long id) {
        ProductResponseDto productResponseDto = productService.getProduct(id);
        // 상품정보 가져오기
        List<QuestionResponseDto> questionResponseDtos = productService.getQuestion(id);
        // 상품에 해당하는 질문리스트 가져오기
        List<ReviewResponseDto> reviewResponseDtos = productService.getReview(id);
        // 상품에 해당하는 후기리스트 가져오기

        return new DetailResult(productResponseDto, questionResponseDtos, reviewResponseDtos);
    }

    //   0원렌탈,브랜드 API
    @GetMapping(value = "/zero-rental")    // 0원렌탈 API
    public List<ProductResponseDto> getzeroCardPage(){
        List<ProductResponseDto> zero = productService.getZeroRental(600000);

        return zero;
    }

    @GetMapping(value = "/brand")           // 브랜드 API
    public List<ProductResponseDto> getAllProduct() {
        List<ProductResponseDto> all = productService.getAllProduct();

        return all;
    }

    @GetMapping(value = "/brand/{brand_name}") // 브랜드별로 상품 리스트 가져오기
    public List<ProductResponseDto> getByBrand(String brand_name) {

        List<ProductResponseDto> brandProduct = productService.getProductListByBrand(brand_name);

        return brandProduct;
    }


    //          후기글 return API
    @GetMapping(value = "/review") // 후기글 페이징처리해서 가져오기
    public List<ReviewResponseDto> getReview(String content, int page) {

        if (content == null) {
            content = "";
        }

        List<ReviewResponseDto> reviews = reviewService.getReviewPage(content, page); // ReviewResponseDto로 반환됨

        return reviews;
    }

    /////////////////////////////////////////////////////////////////////////



    //          견적신청-전자기기신청 API -> 관리자DB로 넘어간다 (PC,모니터)
    @PostMapping(value = "/electronics/pcm/apply")
    public ResponseEntity<EstimateElecResponseDto> applyElec(@RequestBody EstimateElecDto estimateElecDto) {
        EstimateElecResponseDto estimateElecResponseDto = estimateElecService.saveEstimateElecDto(estimateElecDto);

        return ResponseEntity.status(HttpStatus.OK).body(estimateElecResponseDto);
    }

    //          견적신청-전자기기신청 API -> 관리자DB로 넘어간다 (TV)
    @PostMapping(value = "/electronics/tv/apply")
    public ResponseEntity<EstimateTVResponseDto> applyTV(@RequestBody EstimateTVDto estimateTVDto) {
        EstimateTVResponseDto estimateTVResponseDto = estimateTVService.saveEstimateTVDto(estimateTVDto);

        return ResponseEntity.status(HttpStatus.OK).body(estimateTVResponseDto);
    }

    //          견적신청-행사용품신청 API -> 관리자DB로 넘어간다
    @PostMapping(value = "/event/apply")
    public ResponseEntity<EstimateEventResponseDto> applyEvent(@RequestBody EstimateEventDto estimateEventDto) {
        EstimateEventResponseDto estimateEventResponseDto = estimateEventService.saveEstimateEventDto(estimateEventDto);

        return ResponseEntity.status(HttpStatus.OK).body(estimateEventResponseDto);
    }

    //          파트너스권한신청 API -> 관리자DB로 넘어간다
    @PostMapping(value = "/partners/apply")
    public ResponseEntity<ApplymentPartnersResponseDto> applyPartners(@RequestBody ApplymentPartnersDto applymentPartnersDto) {
        ApplymentPartnersResponseDto applymentPartnersResponseDto =
                applymentPartnersService.saveApplymentPartnersDto(applymentPartnersDto);

        return ResponseEntity.status(HttpStatus.OK).body(applymentPartnersResponseDto);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////

    // 등록 API

    @PostMapping(value = "register/product") // 상품등록하기
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto) {
        ProductResponseDto productResponseDto = productService.saveProductDto(productDto);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PostMapping(value = "register/question") // 질문등록하기//////////////////////////////////////////////////////////
    public ResponseEntity<QuestionResponseDto> createQuestion(@RequestBody QuestionDto questionDto) {
        QuestionResponseDto questionResponseDto = questionService.saveQuestionDto(questionDto);

        return ResponseEntity.status(HttpStatus.OK).body(questionResponseDto);
    }

    @PostMapping(value = "register/member") // 회원 만들기
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody MemberDto memberDto) {
        MemberResponseDto memberResponseDto = memberService.saveMemberDto(memberDto);

        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDto);
    }

    @PostMapping(value = "register/review") // 후기 등록하기
    public ResponseEntity<ReviewResponseDto> createReview(@RequestBody ReviewDto reviewDto) {
        ReviewResponseDto reviewResponseDto = reviewService.saveReviewDto(reviewDto);

        return ResponseEntity.status(HttpStatus.OK).body(reviewResponseDto);
    }

}

