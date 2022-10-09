package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.*;
import com.springboot.gguda.data.entity.Coupon;
import com.springboot.gguda.data.repository.CouponRepository;
import com.springboot.gguda.data.repository.MemberRepository;
import com.springboot.gguda.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public CouponServiceImpl(CouponRepository couponRepository, MemberRepository memberRepository) {
        this.couponRepository = couponRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public List<CouponResponseDto> getCouponList(String memberId, Integer isUsed) {

        List<Coupon> coupons = couponRepository.findAllByMemberIdAndIsUsedOrderByCreatedAtDesc(memberId, isUsed);

        List<CouponResponseDto> couponResponseDtosList = new ArrayList<>();

        for(Coupon coupon : coupons) {
            CouponResponseDto dto = CouponResponseDto.builder()
                    .id(coupon.getId())
                    .num(coupon.getNum())
                    .name(coupon.getName())
                    .rate(coupon.getRate())
                    .exPeriod(coupon.getExPeriod())
                    .use(coupon.getIsUsed())
                    .memberId(coupon.getMemberId())
                    .createdAt(coupon.getCreatedAt())
                    .updatedAt(coupon.getUpdatedAt())
                    .build();

            couponResponseDtosList.add(dto);
        }

        return couponResponseDtosList;

    }

    @Override
    public CouponResponseDto saveCouponDto(CouponDto couponDto) {
        Coupon coupon = new Coupon();
        coupon.setRate(couponDto.getRate());
        coupon.setNum(couponDto.getNum());
        coupon.setName(couponDto.getName());
        coupon.setExPeriod(couponDto.getExPeriod());
        coupon.setIsUsed(couponDto.getUse());
        coupon.setMemberId(couponDto.getMemberId());

        couponRepository.save(coupon);

        CouponResponseDto couponResponseDto = new CouponResponseDto();
        couponResponseDto.setId(coupon.getId());
        couponResponseDto.setCreatedAt(coupon.getCreatedAt());
        couponResponseDto.setUpdatedAt(coupon.getUpdatedAt());
        couponResponseDto.setRate(coupon.getRate());
        couponResponseDto.setNum(coupon.getNum());
        couponResponseDto.setName(coupon.getName());
        couponResponseDto.setExPeriod(coupon.getExPeriod());
        couponResponseDto.setUse(coupon.getIsUsed());
        couponResponseDto.setMemberId(coupon.getMemberId());

        return couponResponseDto;
    }

    @Override
    public CouponResponseDto getCouponAdminEntity(Long num, String memberId) {
        Coupon coupon = couponRepository.findByNum(num);

        coupon.setMemberId(memberId);
        couponRepository.save(coupon);

        CouponResponseDto couponResponseDto = new CouponResponseDto();
        couponResponseDto.setId(coupon.getId());
        couponResponseDto.setCreatedAt(coupon.getCreatedAt());
        couponResponseDto.setUpdatedAt(coupon.getUpdatedAt());
        couponResponseDto.setRate(coupon.getRate());
        couponResponseDto.setNum(coupon.getNum());
        couponResponseDto.setName(coupon.getName());
        couponResponseDto.setExPeriod(coupon.getExPeriod());
        couponResponseDto.setUse(coupon.getIsUsed());
        couponResponseDto.setMemberId(coupon.getMemberId());

        return couponResponseDto;
    }

    @Override
    public Double getCouponDiscountRate(Long couponId) {
        Coupon coupon = couponRepository.getById(couponId);
        Double discountRate = coupon.getRate();

        return discountRate;
    }

}
