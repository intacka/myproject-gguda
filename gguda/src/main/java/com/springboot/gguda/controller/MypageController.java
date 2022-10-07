package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.*;
import com.springboot.gguda.data.entity.CouponAdmin;
import com.springboot.gguda.data.entity.Member;
import com.springboot.gguda.data.repository.CouponAdminRepository;
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
    private final CouponAdminRepository couponAdminRepository;
    private final CouponAdminService couponAdminService;
    private final ReviewService reviewService;
    private final EventReviewService eventReviewService;
    private final QuestionService questionService;
    private final EventQuestionService eventQuestionService;
    private final OrderHistoryService orderHistoryService;
    private final BasketService basketService;
    private final EventBasketService eventBasketService;

    @Autowired
    public MypageController(MemberService memberService,
                            MemberRepository memberRepository,
                            CouponAdminService couponAdminService,
                            ReviewService reviewService,
                            EventReviewService eventReviewService,
                            QuestionService questionService,
                            EventQuestionService eventQuestionService,
                            OrderHistoryService orderHistoryService,
                            CouponAdminRepository couponAdminRepository,
                            BasketService basketService,
                            EventBasketService eventBasketService) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.couponAdminService = couponAdminService;
        this.reviewService = reviewService;
        this.eventReviewService = eventReviewService;
        this.questionService = questionService;
        this.eventQuestionService = eventQuestionService;
        this.orderHistoryService = orderHistoryService;
        this.couponAdminRepository = couponAdminRepository;
        this.basketService = basketService;
        this.eventBasketService = eventBasketService;
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
            member.setBuisnessReg(null);

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

    @GetMapping(value = "/coupon")             // 회원MemberId(String)이 가지고있는 쿠폰조회 API
    public List<CouponAdminResponseDto> getCouponList(String id) {
        List<CouponAdminResponseDto> couponAdminResponseDtos = couponAdminService.getCouponList(id);

        return couponAdminResponseDtos;
    }

    @GetMapping(value = "/myWriting")        // 회원id(memberId)로 후기질문들 찾기
    public WritingResult getWritingList(Long id) {
        List<ReviewResponseDto> reviewResponseDtos = reviewService.getReviewList(id);
        List<EventReviewResponseDto> eventReviewResponseDtos = eventReviewService.getEventReviewList(id);
        List<QuestionResponseDto> questionResponseDtos = questionService.getQuestionList(id);
        List<EventQuestionResponseDto> eventQuestionResponseDtos = eventQuestionService.getEventQuestionList(id);

        return new WritingResult(reviewResponseDtos, eventReviewResponseDtos, questionResponseDtos, eventQuestionResponseDtos);
    }

    @GetMapping(value = "/OrderHistory")     // 회원id로 주문내역 return  API
    public List<OrderHistoryResponseDto> getOrderHistoryList(Long id) {
        List<OrderHistoryResponseDto> orderHistoryResponseDtos = orderHistoryService.getOrderHistoryList(id);

        return orderHistoryResponseDtos;
    }

    @PostMapping(value = "/register/coupon/user") // 쿠폰등록하기(((((사용자용입니다))))) (일련번호로 등록하기)
    public CouponRegResult createCoupon(Long num, String memberId) {
        CouponAdminResponseDto couponAdminResponseDto = null;
        String state;
        int N;

        CouponAdmin couponAdmin = couponAdminRepository.findByNum(num);

        if (couponAdmin!=null) {
            if(couponAdmin.getMemberId()==null) {
                couponAdminResponseDto = couponAdminService.getCouponAdminEntity(num, memberId);
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


        return new CouponRegResult(N, state, couponAdminResponseDto);
    }

    @PostMapping(value = "/register/coupon/admin") // 쿠폰등록하기(((((관리자용입니다)))))
    public ResponseEntity<CouponAdminResponseDto> createCoupon(@RequestBody CouponAdminDto couponAdminDto) {
        CouponAdminResponseDto couponAdminResponseDto = couponAdminService.saveCouponDto(couponAdminDto);

        return ResponseEntity.status(HttpStatus.OK).body(couponAdminResponseDto);
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

}
