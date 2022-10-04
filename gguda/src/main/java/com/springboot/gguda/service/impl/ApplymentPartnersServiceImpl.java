package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.apply.ApplymentPartnersDto;
import com.springboot.gguda.data.dto.apply.ApplymentPartnersResponseDto;
import com.springboot.gguda.data.dto.apply.EstimateElecDto;
import com.springboot.gguda.data.dto.apply.EstimateElecResponseDto;
import com.springboot.gguda.data.entity.apply.ApplymentPartners;
import com.springboot.gguda.data.entity.apply.EstimateElec;
import com.springboot.gguda.data.repository.ApplymentPartnersRepository;
import com.springboot.gguda.data.repository.EstimateElecRepository;
import com.springboot.gguda.data.repository.MemberRepository;
import com.springboot.gguda.service.ApplymentPartnersService;
import com.springboot.gguda.service.EstimateElecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplymentPartnersServiceImpl implements ApplymentPartnersService {


    private final MemberRepository memberRepository;
    private final ApplymentPartnersRepository applymentPartnersRepository;

    @Autowired
    public ApplymentPartnersServiceImpl(MemberRepository memberRepository, ApplymentPartnersRepository applymentPartnersRepository) {
        this.memberRepository = memberRepository;
        this.applymentPartnersRepository = applymentPartnersRepository;
    }


    @Override
    public ApplymentPartnersResponseDto saveApplymentPartnersDto(ApplymentPartnersDto applymentPartnersDto) {
        ApplymentPartners applymentPartners = new ApplymentPartners();
        applymentPartners.setBusinessName(applymentPartnersDto.getBusinessName());
        applymentPartners.setBusinessNum(applymentPartnersDto.getBusinessNum());
        applymentPartners.setRepresentative(applymentPartnersDto.getRepresentative());
        applymentPartners.setBusinessAddress(applymentPartnersDto.getBusinessAddress());
        applymentPartners.setServiceName(applymentPartnersDto.getServiceName());
        applymentPartners.setSiteAddress(applymentPartnersDto.getSiteAddress());
        applymentPartners.setStoreLocation(applymentPartnersDto.getStoreLocation());
        applymentPartners.setCategory(applymentPartnersDto.getCategory());
        applymentPartners.setDealMethod(applymentPartnersDto.getDealMethod());
        applymentPartners.setName(applymentPartnersDto.getName());
        applymentPartners.setPhoneNum(applymentPartnersDto.getPhoneNum());
        applymentPartners.setGgudaId(applymentPartnersDto.getGgudaId());
        applymentPartners.setAdminId(applymentPartnersDto.getAdminId());
        applymentPartners.setEtcContent(applymentPartnersDto.getEtcContent());
        applymentPartners.setMember(memberRepository.findById(applymentPartnersDto.getMemberId()).get());

        applymentPartnersRepository.save(applymentPartners);

        ApplymentPartnersResponseDto applymentPartnersResponseDto = new ApplymentPartnersResponseDto();
        applymentPartnersResponseDto.setId(applymentPartners.getId());
        applymentPartnersResponseDto.setBusinessName(applymentPartners.getBusinessName());
        applymentPartnersResponseDto.setBusinessNum(applymentPartners.getBusinessNum());
        applymentPartnersResponseDto.setRepresentative(applymentPartners.getRepresentative());
        applymentPartnersResponseDto.setBusinessAddress(applymentPartners.getBusinessAddress());
        applymentPartnersResponseDto.setServiceName(applymentPartners.getServiceName());
        applymentPartnersResponseDto.setSiteAddress(applymentPartners.getSiteAddress());
        applymentPartnersResponseDto.setStoreLocation(applymentPartners.getStoreLocation());
        applymentPartnersResponseDto.setCategory(applymentPartners.getCategory());
        applymentPartnersResponseDto.setDealMethod(applymentPartners.getDealMethod());
        applymentPartnersResponseDto.setName(applymentPartners.getName());
        applymentPartnersResponseDto.setPhoneNum(applymentPartners.getPhoneNum());
        applymentPartnersResponseDto.setGgudaId(applymentPartners.getGgudaId());
        applymentPartnersResponseDto.setAdminId(applymentPartners.getAdminId());
        applymentPartnersResponseDto.setEtcContent(applymentPartners.getEtcContent());
        applymentPartnersResponseDto.setMemberId(applymentPartners.getMember().getId());
        applymentPartnersResponseDto.setCreatedAt(applymentPartners.getCreatedAt());
        applymentPartnersResponseDto.setUpdatedAt(applymentPartners.getUpdatedAt());

        return applymentPartnersResponseDto;
    }
}
