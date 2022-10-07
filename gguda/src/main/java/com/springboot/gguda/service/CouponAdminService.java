package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.CouponAdminDto;
import com.springboot.gguda.data.dto.CouponAdminResponseDto;

import java.util.List;

public interface CouponAdminService {


    List<CouponAdminResponseDto> getCouponList(String id);

    CouponAdminResponseDto saveCouponDto(CouponAdminDto couponAdminDto);

    CouponAdminResponseDto getCouponAdminEntity(Long num, String memberId); // 쿠폰 일련번호로 찾기
}
