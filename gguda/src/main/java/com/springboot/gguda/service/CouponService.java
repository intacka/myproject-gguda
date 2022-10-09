package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.CouponDto;
import com.springboot.gguda.data.dto.CouponResponseDto;

import java.util.List;

public interface CouponService {


    List<CouponResponseDto> getCouponList(String memberId, Integer isUsed);

    CouponResponseDto saveCouponDto(CouponDto couponDto);

    CouponResponseDto getCouponAdminEntity(Long num, String memberId); // 쿠폰 일련번호로 찾기

    Double getCouponDiscountRate(Long couponId);
}
