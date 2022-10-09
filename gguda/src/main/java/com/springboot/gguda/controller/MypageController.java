package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.*;
import com.springboot.gguda.data.entity.Coupon;
import com.springboot.gguda.data.entity.Member;
import com.springboot.gguda.data.repository.CouponRepository;
import com.springboot.gguda.data.repository.MemberRepository;
import com.springboot.gguda.result.*;
import com.springboot.gguda.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/myPage")
public class MypageController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;
    private final CouponService couponService;
    private final ReviewService reviewService;
    private final EventReviewService eventReviewService;
    private final QuestionService questionService;
    private final EventQuestionService eventQuestionService;
    private final BasketService basketService;
    private final EventBasketService eventBasketService;
    private final ReserveHistoryService reserveHistoryService;
    private final PurchaseService purchaseService;

    @Autowired
    public MypageController(MemberService memberService,
                            MemberRepository memberRepository,
                            CouponService couponService,
                            ReviewService reviewService,
                            EventReviewService eventReviewService,
                            QuestionService questionService,
                            EventQuestionService eventQuestionService,
                            CouponRepository couponRepository,
                            BasketService basketService,
                            EventBasketService eventBasketService,
                            ReserveHistoryService reserveHistoryService,
                            PurchaseService purchaseService) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.couponService = couponService;
        this.reviewService = reviewService;
        this.eventReviewService = eventReviewService;
        this.questionService = questionService;
        this.eventQuestionService = eventQuestionService;
        this.couponRepository = couponRepository;
        this.basketService = basketService;
        this.eventBasketService = eventBasketService;
        this.reserveHistoryService = reserveHistoryService;
        this.purchaseService = purchaseService;
    }

    @PostMapping(value = "/delete")         // 회원탈퇴 API
    public MemberResult dropMember(@RequestParam Long id, String memberPw) {

        Optional<Member> optionalMember = memberRepository.findById(id);
        Member member = optionalMember.get();

        MemberResponseDto memberResponseDto = new MemberResponseDto();

        if(memberPw.equals(member.getMemberPw())) {
            member.setMemberPw(null);
            member.setEmail(null);
            member.setParterAutho(null);
            member.setPhoneNum(null);
            member.setAddress(null);
            member.setGender(null);
            member.setDateOfBirth(null);
            member.setMarketingConsent(null);
            member.setReserves(null);

            memberResponseDto.setId(member.getId());
            memberResponseDto.setMemberId(member.getMemberId());
            memberResponseDto.setCreatedAt(member.getCreatedAt());
            memberResponseDto.setUpdatedAt(member.getUpdatedAt());

            return new MemberResult(memberResponseDto, true);
        } else {
            return new MemberResult(null, false);
        }

    }

    @GetMapping(value = "/memberInfo")             //회원정보 return API
    public MemberResponseDto getMember(Long id) {
        MemberResponseDto myInfo = memberService.getMember(id);

        return myInfo;
    }

    @GetMapping(value = "/myWriting")        // 회원id(memberId)로 후기질문들 찾기
    public WritingResult getWritingList(Long id) {
        List<ReviewResponseDto> reviewResponseDtos = reviewService.getReviewList(id);
        List<EventReviewResponseDto> eventReviewResponseDtos = eventReviewService.getEventReviewList(id);
        List<QuestionResponseDto> questionResponseDtos = questionService.getQuestionList(id);
        List<EventQuestionResponseDto> eventQuestionResponseDtos = eventQuestionService.getEventQuestionList(id);

        return new WritingResult(reviewResponseDtos, eventReviewResponseDtos, questionResponseDtos, eventQuestionResponseDtos);
    }



    @GetMapping(value = "/coupon")             // 회원MemberId(String)이 가지고있는 <<사용가능한(use:0)>>쿠폰조회 API
    public List<CouponResponseDto> getCouponList(String stringId) {
        List<CouponResponseDto> couponResponseDtos = couponService.getCouponList(stringId, 0);

        return couponResponseDtos;
    }

    @GetMapping(value = "/coupon/isused")             // 회원MemberId(String)이 사용했던  <<사용했던(use:1)>>쿠폰조회 API
    public List<CouponResponseDto> getUsedCouponList(String stringId) {
        List<CouponResponseDto> couponResponseDtos = couponService.getCouponList(stringId, 1);

        return couponResponseDtos;
    }

    @PostMapping(value = "/register/coupon/user") // 쿠폰등록하기(((((사용자용입니다))))) (일련번호로 등록하기)
    public CouponRegResult createCoupon(Long num, String memberId) {
        CouponResponseDto couponResponseDto = null;
        String state;
        int N;

        Coupon coupon = couponRepository.findByNum(num);

        if (coupon !=null) {
            if(coupon.getMemberId()==null) {
                couponResponseDto = couponService.getCouponAdminEntity(num, memberId);
                state = "성공적으로 쿠폰이 사용자에게 등록되었습니다.";
                N = 0;
            } else {
                state = "다른사람에게 이미 등록된 쿠폰입니다!"; // 쿠폰을 다른사람이 쓰고있습니다!.
                N = 1;
            }

        } else {
            state = "쿠폰일련번호가 맞지 않습니다."; // 쿠폰일련번호가 맞지않습니다.
            N = 2;
        }


        return new CouponRegResult(N, state, couponResponseDto);
    }

    @PostMapping(value = "/register/coupon/admin") // 쿠폰등록하기(((((관리자용입니다)))))
    public ResponseEntity<CouponResponseDto> createCoupon(@RequestBody CouponDto couponDto) {
        CouponResponseDto couponResponseDto = couponService.saveCouponDto(couponDto);

        return ResponseEntity.status(HttpStatus.OK).body(couponResponseDto);
    }

    @PostMapping(value = "/addtion/basket")                      // 장바구니에 추가하기
    public ResponseEntity<BasketResponseDto> addProductInBasket(Long productId, Long memberId, Long amount) {
        BasketResponseDto basketResponseDto = basketService.addInBasket(productId, memberId, amount);

        return ResponseEntity.status(HttpStatus.OK).body(basketResponseDto);
    }

    @GetMapping(value = "/basket")               //   장바구니에 담긴 Product List return하기
    public List<BasketResult> getBasketProductList(Long memberId) {
        List<BasketResult> basketResultList = basketService.getBasketProductList(memberId);

        return basketResultList;
    }

    // 일반상품 장바구니에 있는 총가격 return
    @PostMapping(value="/basket/total-price")
    public Long getTotalPrice(Long memberId) {
        Long totalPrice = basketService.getTotalPrice(memberId);
        return totalPrice;
    }

    @PutMapping(value="/basket/put")                // 장바구니 수량수정
    public ResponseEntity<BasketResponseDto> putBasket(Long productId, Long memberId, Long amount) {
        BasketResponseDto basketResponseDto = basketService.putBasket(productId, memberId, amount);

        return ResponseEntity.status(HttpStatus.OK).body(basketResponseDto);
    }

    @DeleteMapping(value = "/basket-product/delete")        // 장바구니 목록 삭제 (장바구니id줘야함)
    public boolean deleteBasketProduct(Long id) {
        boolean result = basketService.deleteBasketProduct(id);
        return result;
    }

    @PostMapping(value = "/addtion/event-basket")                      // 행사바구니에 행사용품 추가하기
    public ResponseEntity<EventBasketResponseDto> addEventProductInEventBasket(Long eventProductId, Long memberId, Long amount) {
        EventBasketResponseDto eventBasketResponseDto = eventBasketService.addInEventBasket(eventProductId, memberId, amount);

        return ResponseEntity.status(HttpStatus.OK).body(eventBasketResponseDto);
    }

    @GetMapping(value = "/event-basket")               //   행사바구니에 담긴 행사용품 List 나타내기
    public List<EventBasketResult> getEventBasketProductList(Long memberId) {
        List<EventBasketResult> eventBasketResultList = eventBasketService.getEventBasketEventProductList(memberId);

        return eventBasketResultList;
    }

    @PutMapping(value="/event-basket/put")                // 행사바구니 수량수정
    public ResponseEntity<EventBasketResponseDto> putEventBasket(Long eventProductId, Long memberId, Long amount) {
        EventBasketResponseDto eventBasketResponseDto = eventBasketService.putEventBasket(eventProductId, memberId, amount);

        return ResponseEntity.status(HttpStatus.OK).body(eventBasketResponseDto);
    }

    @DeleteMapping(value = "/event-basket-product/delete")        // 행사바구니 목록 삭제 (행사바구니id줘야함)
    public boolean deleteEventBasketEventProduct(Long id) {
        boolean result = eventBasketService.deleteEventBasketEventProduct(id);
        return result;
    }

    // 결제할 총 금액 return (구매직전)
    @PostMapping(value = "/total-price/view")
    public Double getTotalPrice(Long basketTotalPrice, Long couponId, Long reserve) {
        Double couponDiscountRate;

        if (reserve == null) {
            reserve = 0L;
        }

        if (couponId!=null) {
            couponDiscountRate = couponService.getCouponDiscountRate(couponId);
        } else {
            couponDiscountRate = 0.0;
        }

        Double totalPrice = (basketTotalPrice-(basketTotalPrice*couponDiscountRate)-reserve);
        return totalPrice;
    }

    @GetMapping(value = "/reserve/view")   //         적립금내역 조회하기
    public List<ReserveHistoryResponseDto> getAllReserveHistory(Long memberId){
        List<ReserveHistoryResponseDto> ReserveHistoryList = reserveHistoryService.getAllReserveHistory(memberId);

        return ReserveHistoryList;
    }

    @PostMapping(value = "/reserve/allow")      //   적립금 변동시키기 API
    public ReserveHistoryResponseDto createReserveHistory(ReserveHistoryDto reserveHistoryDto) {
        ReserveHistoryResponseDto reserveHistoryResponseDto = reserveHistoryService.createReserveHistory(reserveHistoryDto);

        return reserveHistoryResponseDto;
    }

    @GetMapping(value = "/purchase-history")     // 회원id로 final 구매내역모두 (List) return  API
    public List<PurchaseResult> getPurchaseList(Long id) {
        List<PurchaseResult> purchaseResults = purchaseService.getPurchaseList(id);

        return purchaseResults;
    }

    @GetMapping(value = "/purchase-history/detail")     // 구매내역 고유id로 final 구매내역 Detail return  API
    public PurchaseResult getPurchase(Long id) {
        PurchaseResult purchaseResult = purchaseService.getPurchaseDetail(id);

        return purchaseResult;
    }

}
