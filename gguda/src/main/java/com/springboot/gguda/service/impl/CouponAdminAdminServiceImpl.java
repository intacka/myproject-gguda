package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.*;
import com.springboot.gguda.data.entity.CouponAdmin;
import com.springboot.gguda.data.repository.CouponAdminRepository;
import com.springboot.gguda.data.repository.MemberRepository;
import com.springboot.gguda.service.CouponAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CouponAdminAdminServiceImpl implements CouponAdminService {

    private final CouponAdminRepository couponAdminRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public CouponAdminAdminServiceImpl(CouponAdminRepository couponAdminRepository, MemberRepository memberRepository) {
        this.couponAdminRepository = couponAdminRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public List<CouponAdminResponseDto> getCouponList(Long id) {

        List<CouponAdmin> couponAdmins = couponAdminRepository.findAllByMemberIdOrderByCreatedAtDesc(id);

        List<CouponAdminResponseDto> couponAdminResponseDtosList = new ArrayList<>();

        for(CouponAdmin couponAdmin : couponAdmins) {
            CouponAdminResponseDto dto = CouponAdminResponseDto.builder()
                    .id(couponAdmin.getId())
                    .num(couponAdmin.getNum())
                    .name(couponAdmin.getName())
                    .rate(couponAdmin.getRate())
                    .exPeriod(couponAdmin.getExPeriod())
                    .memberId(couponAdmin.getMember().getId())
                    .createdAt(couponAdmin.getCreatedAt())
                    .updatedAt(couponAdmin.getUpdatedAt())
                    .build();

            couponAdminResponseDtosList.add(dto);
        }

        return couponAdminResponseDtosList;

    }

    @Override
    public CouponAdminResponseDto saveCouponDto(CouponAdminDto couponAdminDto) {
        CouponAdmin couponAdmin = new CouponAdmin();
        couponAdmin.setRate(couponAdminDto.getRate());
        couponAdmin.setNum(couponAdminDto.getNum());
        couponAdmin.setName(couponAdminDto.getName());
        couponAdmin.setExPeriod(couponAdminDto.getExPeriod());
        couponAdmin.setMember(memberRepository.findById(couponAdminDto.getMemberId()).get());

        couponAdminRepository.save(couponAdmin);

        CouponAdminResponseDto couponAdminResponseDto = new CouponAdminResponseDto();
        couponAdminResponseDto.setId(couponAdmin.getId());
        couponAdminResponseDto.setCreatedAt(couponAdmin.getCreatedAt());
        couponAdminResponseDto.setUpdatedAt(couponAdmin.getUpdatedAt());
        couponAdminResponseDto.setRate(couponAdmin.getRate());
        couponAdminResponseDto.setNum(couponAdmin.getNum());
        couponAdminResponseDto.setName(couponAdmin.getName());
        couponAdminResponseDto.setExPeriod(couponAdmin.getExPeriod());
        couponAdminResponseDto.setMemberId(couponAdmin.getMember().getId());

        return couponAdminResponseDto;
    }
}
