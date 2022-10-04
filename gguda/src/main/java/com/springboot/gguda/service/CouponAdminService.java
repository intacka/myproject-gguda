package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.CouponAdminDto;
import com.springboot.gguda.data.dto.CouponAdminResponseDto;

import java.util.List;

public interface CouponAdminService {


    List<CouponAdminResponseDto> getCouponList(Long id);

    CouponAdminResponseDto saveCouponDto(CouponAdminDto couponAdminDto);
}
