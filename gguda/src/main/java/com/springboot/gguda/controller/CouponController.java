package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.CouponDto;
import com.springboot.gguda.data.dto.CouponResponseDto;
import com.springboot.gguda.data.entity.Coupon;
import com.springboot.gguda.data.repository.CouponRepository;
import com.springboot.gguda.result.CouponRegResult;
import com.springboot.gguda.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;
    private final CouponRepository couponRepository;

    @Autowired
    public CouponController(CouponService couponService,
                            CouponRepository couponRepository) {
        this.couponService = couponService;
        this.couponRepository = couponRepository;
    }


    // 회원MemberId(String)이 가지고있는 <<사용가능한(use:0)>>쿠폰조회 API
    @GetMapping(value = "/view-unused")
    public List<CouponResponseDto> getCouponList(String stringId) {
        List<CouponResponseDto> couponResponseDtos = couponService.getCouponList(stringId, 0);

        return couponResponseDtos;
    }

    // 회원MemberId(String)이 사용했던  <<사용했던(use:1)>>쿠폰조회 API
    @GetMapping(value = "/view-isused")
    public List<CouponResponseDto> getUsedCouponList(String stringId) {
        List<CouponResponseDto> couponResponseDtos = couponService.getCouponList(stringId, 1);

        return couponResponseDtos;
    }

    // 쿠폰등록하기(((((사용자용입니다))))) (일련번호로 등록하기)
    @PostMapping(value = "/register-user")
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

    // 쿠폰등록하기(((((관리자용입니다)))))
    @PostMapping(value = "/register-admin")
    public ResponseEntity<CouponResponseDto> createCoupon(@RequestBody CouponDto couponDto) {
        CouponResponseDto couponResponseDto = couponService.saveCouponDto(couponDto);

        return ResponseEntity.status(HttpStatus.OK).body(couponResponseDto);
    }

}
