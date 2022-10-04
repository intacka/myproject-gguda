package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.*;
import com.springboot.gguda.data.entity.Member;
import com.springboot.gguda.data.repository.MemberRepository;
import com.springboot.gguda.result.MemberResult;
import com.springboot.gguda.result.WritingResult;
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
    private final CouponAdminService couponAdminService;
    private final ReviewService reviewService;
    private final EventReviewService eventReviewService;
    private final QuestionService questionService;
    private final EventQuestionService eventQuestionService;
    private final OrderHistoryService orderHistoryService;

    @Autowired
    public MypageController(MemberService memberService,
                            MemberRepository memberRepository,
                            CouponAdminService couponAdminService,
                            ReviewService reviewService,
                            EventReviewService eventReviewService,
                            QuestionService questionService,
                            EventQuestionService eventQuestionService,
                            OrderHistoryService orderHistoryService) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.couponAdminService = couponAdminService;
        this.reviewService = reviewService;
        this.eventReviewService = eventReviewService;
        this.questionService = questionService;
        this.eventQuestionService = eventQuestionService;
        this.orderHistoryService = orderHistoryService;
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

    @GetMapping(value = "memberInfo")             //회원정보 return API
    public MemberResponseDto getMember(Long id) {
        MemberResponseDto myInfo = memberService.getMember(id);

        return myInfo;
    }

    @GetMapping(value = "coupon")             // 회원이 가지고있는 쿠폰조회 API
    public List<CouponAdminResponseDto> getCouponList(Long id) {
        List<CouponAdminResponseDto> couponAdminResponseDtos = couponAdminService.getCouponList(id);

        return couponAdminResponseDtos;
    }

    @GetMapping(value = "myWriting")        // 회원id(memberId)로 후기질문들 찾기
    public WritingResult getWritingList(Long id) {
        List<ReviewResponseDto> reviewResponseDtos = reviewService.getReviewList(id);
        List<EventReviewResponseDto> eventReviewResponseDtos = eventReviewService.getEventReviewList(id);
        List<QuestionResponseDto> questionResponseDtos = questionService.getQuestionList(id);
        List<EventQuestionResponseDto> eventQuestionResponseDtos = eventQuestionService.getEventQuestionList(id);

        return new WritingResult(reviewResponseDtos, eventReviewResponseDtos, questionResponseDtos, eventQuestionResponseDtos);
    }

    @GetMapping(value = "OrderHistory")     // 회원id로 주문내역 return  API
    public List<OrderHistoryResponseDto> getOrderHistoryList(Long id) {
        List<OrderHistoryResponseDto> orderHistoryResponseDtos = orderHistoryService.getOrderHistoryList(id);

        return orderHistoryResponseDtos;
    }

//    @PostMapping(value = "register/coupon/user") // 쿠폰등록하기(((((사용자용입니다)))))
//    public ResponseEntity<CouponResponseDto> createCoupon(String num) {
//        CouponResponseDto couponResponseDto = couponService.saveCouponDto(couponDto);
//
//        return ResponseEntity.status(HttpStatus.OK).body(couponResponseDto);
//    }

    @PostMapping(value = "register/coupon/admin") // 쿠폰등록하기(((((관리자용입니다)))))
    public ResponseEntity<CouponAdminResponseDto> createCoupon(@RequestBody CouponAdminDto couponAdminDto) {
        CouponAdminResponseDto couponAdminResponseDto = couponAdminService.saveCouponDto(couponAdminDto);

        return ResponseEntity.status(HttpStatus.OK).body(couponAdminResponseDto);
    }


}
