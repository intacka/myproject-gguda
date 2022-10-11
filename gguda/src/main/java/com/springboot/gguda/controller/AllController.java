package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.*;
import com.springboot.gguda.data.dto.apply.*;
import com.springboot.gguda.data.repository.CouponRepository;
import com.springboot.gguda.result.DetailResult;
import com.springboot.gguda.result.MainResult;
import com.springboot.gguda.result.PurchaseResult;
import com.springboot.gguda.result.QAResult;
import com.springboot.gguda.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AllController {

    private final ProductService productService;
    private final QuestionService questionService;
    private final QuestionAnswerService questionAnswerService;

    private final MemberService memberService;
    private final ReviewService reviewService;
    private final EstimateElecService estimateElecService;
    private final EstimateTVService estimateTVService;
    private final EstimateEventService estimateEventService;
    private final ApplymentPartnersService applymentPartnersService;
    private final ReserveHistoryService reserveHistoryService;
    private final PurchaseService purchaseService;
    private final BasketService basketService;
    private final CouponRepository couponRepository;
    private final RentalService rentalService;
    private final RentalStopReqService rentalStopReqService;

    @Autowired
    public AllController(ProductService productService,
                         MemberService memberService,
                         ReviewService reviewService,
                         EstimateElecService estimateElecService,
                         EstimateTVService estimateTVService,
                         ApplymentPartnersService applymentPartnersService,
                         EstimateEventService estimateEventService,
                         QuestionService questionService,
                         QuestionAnswerService questionAnswerService,
                         ReserveHistoryService reserveHistoryService,
                         PurchaseService purchaseService,
                         BasketService basketService,
                         CouponRepository couponRepository,
                         RentalService rentalService,
                         RentalStopReqService rentalStopReqService) {
        this.productService = productService;
        this.questionService = questionService;
        this.questionAnswerService = questionAnswerService;
        this.memberService = memberService;
        this.reviewService = reviewService;
        this.estimateElecService = estimateElecService;
        this.estimateTVService = estimateTVService;
        this.applymentPartnersService = applymentPartnersService;
        this.estimateEventService = estimateEventService;
        this.reserveHistoryService = reserveHistoryService;
        this.purchaseService = purchaseService;
        this.basketService = basketService;
        this.couponRepository = couponRepository;
        this.rentalService = rentalService;
        this.rentalStopReqService = rentalStopReqService;
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
        List<QuestionAnswerResponseDto> questionAnswerResponseDtos = productService.getQuestionAnswer(id);
        // 상품에 해당하는 질문리스트, 답변리스트 가져오기
        List<ReviewResponseDto> reviewResponseDtos = productService.getReview(id);
        // 상품에 해당하는 후기리스트 가져오기

        return new DetailResult(productResponseDto, questionResponseDtos, questionAnswerResponseDtos, reviewResponseDtos);
    }

    //   0원렌탈,브랜드 API
    @GetMapping(value = "/zero-rental")    // 0원렌탈 API
    public List<ProductResponseDto> getzeroCardPage(){
        List<ProductResponseDto> zero = productService.getZeroRental(600000);

        return zero;
    }

    @GetMapping(value = "/brand")           // 브랜드 API (전체상품목록 가져오기)
    public List<ProductResponseDto> getAllProduct() {
        List<ProductResponseDto> all = productService.getAllProduct();

        return all;
    }

    @GetMapping(value = "/brand/{brand_name}") // 브랜드별로 상품 리스트 가져오기
    public List<ProductResponseDto> getByBrand(String brand_name) {

        List<ProductResponseDto> brandProduct = productService.getProductListByBrand(brand_name);

        return brandProduct;
    }

    @GetMapping(value = "/popular-notebook")
    public List<ProductResponseDto> getPopularNotebook(){
        List<ProductResponseDto> samsungNotebook10 = productService.getTop10Product("노트북");

        return samsungNotebook10;
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

    @GetMapping(value = "/review/detail")   // 후기글 Detail 가져오기
    public ReviewResponseDto getReviewDetail(Long id){
        ReviewResponseDto review = reviewService.getReview(id);

        return review;
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

    //          견적신청-행사용품신청 API -> 관리자DB로 넘어간다 ***************************
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

    @PostMapping(value = "/register/product") //        상품등록하기
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto) {
        ProductResponseDto productResponseDto = productService.saveProductDto(productDto);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PutMapping(value = "/put/product") //              상품수정하기
    public ResponseEntity<ProductResponseDto> putProduct(@RequestBody ProductDto productDto, Long id) {
        ProductResponseDto productResponseDto = productService.putProduct(productDto, id);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PostMapping(value = "/delete/product")         //  상품삭제하기
    public ProductResponseDto dropProduct(Long id) {

        ProductResponseDto productResponseDto = productService.deleteProduct(id);

        return productResponseDto;
    }

    @PostMapping(value = "register/question") // 질문등록하기//////////////////////////////////////////////////////////
    public ResponseEntity<QuestionResponseDto> createQuestion(@RequestBody QuestionDto questionDto) {
        QuestionResponseDto questionResponseDto = questionService.saveQuestionDto(questionDto);

        return ResponseEntity.status(HttpStatus.OK).body(questionResponseDto);
    }

    // 답변글 등록
    @PostMapping(value = "register/question-answer") // 답변등록하기//////////////////////////////////////////////////////////
    public ResponseEntity<QuestionAnswerResponseDto> createQuestionAnswer(@RequestBody QuestionAnswerDto questionAnswerDto) {
        QuestionAnswerResponseDto questionAnswerResponseDto = questionAnswerService.saveQuestionAnswerDto(questionAnswerDto);

        return ResponseEntity.status(HttpStatus.OK).body(questionAnswerResponseDto);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 모든질문답변조회 (관리자용)
    @GetMapping(value = "question-answer/view") // 모든 질문,답변 가져오기(관리자용)
    public QAResult getQA() {
        List<QuestionResponseDto> questionResponseDtos = questionService.getAllQuestion();
        List<QuestionAnswerResponseDto> questionAnswerResponseDtos = questionAnswerService.getAllQuestionAnswer();
        // 모든 질문, 답변 리스트로 쫙 가져오기

        return new QAResult(questionResponseDtos, questionAnswerResponseDtos);
    }

    // 답변수정
    @PutMapping(value = "/put/answer") //            답변 수정하기
    public ResponseEntity<QuestionAnswerResponseDto> putQuestionAnswer(@RequestBody QuestionAnswerDto questionAnswerDto, Long id) {
        QuestionAnswerResponseDto questionAnswerResponseDto = questionAnswerService.putQuestionAnswer(questionAnswerDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(questionAnswerResponseDto);
    }

    @DeleteMapping(value = "/delete/answer")//       답변 삭제하기
    public boolean dropQuestionAnswer(Long id) {
        questionAnswerService.deleteQuestionAnswer(id);
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping(value = "register/member") //      회원 만들기 (회원가입) + 적립금 1000원
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody MemberDto memberDto) {
        MemberResponseDto memberResponseDto = memberService.saveMemberDto(memberDto);
        reserveHistoryService.createSigninReserveHistory(memberResponseDto.getId()); // member적립금 1000원추가, 내역도 추가

        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDto);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping(value = "register/review") //       후기 등록하기
    public ResponseEntity<ReviewResponseDto> createReview(@RequestBody ReviewDto reviewDto) {
        ReviewResponseDto reviewResponseDto = reviewService.saveReviewDto(reviewDto);
        reserveHistoryService.createReviewReserveHistory(reviewResponseDto.getMemberId());      // 후기쓰면 50원 적립, 내역도 추가

        return ResponseEntity.status(HttpStatus.OK).body(reviewResponseDto);
    }

    @PutMapping(value = "/put/review") //            후기 수정하기
    public ResponseEntity<ReviewResponseDto> putReview(@RequestBody ReviewDto reviewDto, Long id) {
        ReviewResponseDto reviewResponseDto = reviewService.putReview(reviewDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(reviewResponseDto);
    }

    @DeleteMapping(value = "/delete/review")//       후기 삭제하기
    public boolean dropReview(Long id) {
        reviewService.deleteReview(id);
        return true;
    }

    @GetMapping(value = "/review/all")   //         후기글 모든목록 가져오기 (관리자용)
    public List<ReviewResponseDto> getAllReview(){
        List<ReviewResponseDto> reviews = reviewService.getAllReview();

        return reviews;
    }

    @PostMapping(value = "/login")              // Login API
    public  boolean login(String memberId, String memberPw) {
        boolean result = memberService.login(memberId, memberPw);
        return result;
    }

    @PostMapping(value = "/double-check")       // 회원가입할때 아이디 중복확인
    public boolean doubleCheck(String memberId) {
        boolean result = memberService.doubleCheck(memberId);
        return result;
    }



    // req: purchaseDto를 받아야한다.(여기에 매핑되어있는 배송정보까지)
    // res: purchaseResponseDto를 반환해야한다, 그리고 장바구니List에서 삭제
    @PostMapping(value = "/final-payment")  //  최종결제 API
    public PurchaseResult createPurchase(@RequestBody PurchaseDto purchaseDto) { // 입력받는문제해결해야함
        PurchaseResult purchaseResult = purchaseService.getPurchase(purchaseDto);
        PurchaseResponseDto purchaseResponseDto = purchaseResult.getPurchaseResponseDto();

        ////  장바구니에서 삭제하기
        List<PurchaseProductInfoResponseDto> pPinfoResponseDtos = purchaseResponseDto.getPurchaseProductInfoResponseDtos();
        for(PurchaseProductInfoResponseDto pPinfoResponseDto : pPinfoResponseDtos) {
            basketService.deleteBasketProduct((long)pPinfoResponseDto.getProductId());  // 장바구니 삭제

            Long productId = (long)pPinfoResponseDto.getProductId();
            Integer amount = pPinfoResponseDto.getAmount();
            productService.putStock(productId, amount);                 // 재고량 변동
        }
        // 주문목록은 이미 Purchase에 member로 연관관계매핑이 되어있다. 그러므로 주문목록에는 자동으로 추가가된것이다

        //// 해당쿠폰을 사용상태로 변경
        if (purchaseResponseDto.getCouponId() != null &&
                couponRepository.getById(purchaseResponseDto.getCouponId()).getIsUsed() == 0) {
            couponRepository.getById(purchaseResponseDto.getCouponId()).setIsUsed(1);
        }

        //// 적립금 내역추가, 적립금 변동 - 1%
        Integer reservePrice = (int) (purchaseResponseDto.getTotalPrice() * 0.01); // 적립금액
        reserveHistoryService.createPurchaseReserveHistory(reservePrice, purchaseResponseDto.getMemberId());

        return purchaseResult;
    }

    // 렌탈하기 API
    @PostMapping(value = "/rental-payment")
    public RentalResponseDto createRental(@RequestBody RentalDto rentalDto) {
        RentalResponseDto rentalResponseDto = rentalService.createRental(rentalDto);

        return rentalResponseDto;
    }

    // 렌탈목록 조회 (회원별)
    @GetMapping(value = "/rental-list/view")
    public List<RentalResponseDto> getRentalList(Long memberId) {
        List<RentalResponseDto> rentalResponseDtos = rentalService.getRentalList(memberId);

            return rentalResponseDtos;

    }

    // 렌탈목록조회(전체회원)
    @GetMapping(value = "/rental-list/allview")
    public List<RentalResponseDto> getAllRentalList() {
        List<RentalResponseDto> rentalResponseDtos = rentalService.getAllRentalList();

        return rentalResponseDtos;

    }


    // 렌탈목록 Detail 조회
    @GetMapping(value = "/rental/detail-view")
    public RentalResponseDto getRentalDetail(Long rentalId) {
        RentalResponseDto rentalResponseDto = rentalService.getRentalDetail(rentalId);

        return rentalResponseDto;

    }

    // 렌탈목록 중지요청
    @PostMapping(value = "/rental-stop-req/request")
    public RentalStopReqResponseDto createRentalStopReq(@RequestBody RentalStopReqDto rentalStopReqDto) {
        RentalStopReqResponseDto rentalStopReqResponseDto = rentalStopReqService.createRentalStopReq(rentalStopReqDto);

        return rentalStopReqResponseDto;
    }

    // 렌탈목록 중지요청 조회(all)

    @GetMapping(value = "/rental-stop-req/allview")
    public List<RentalStopReqResponseDto> getAllRentalStopReqList() {
        List<RentalStopReqResponseDto> rentalStopReqResponseDtos = rentalStopReqService.getAllRentalStopReq();

        return rentalStopReqResponseDtos;

    }

}

