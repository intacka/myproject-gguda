package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.data.dto.apply.ApplymentPartnersDto;
import com.springboot.gguda.data.dto.apply.ApplymentPartnersResponseDto;
import com.springboot.gguda.data.dto.apply.EstimateElecDto;
import com.springboot.gguda.data.dto.apply.EstimateElecResponseDto;
import com.springboot.gguda.data.entity.Product;
import com.springboot.gguda.data.entity.apply.ApplymentPartners;
import com.springboot.gguda.data.entity.apply.EstimateElec;
import com.springboot.gguda.data.repository.ApplymentPartnersRepository;
import com.springboot.gguda.data.repository.EstimateElecRepository;
import com.springboot.gguda.data.repository.MemberRepository;
import com.springboot.gguda.service.ApplymentPartnersService;
import com.springboot.gguda.service.EstimateElecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        applymentPartners.setEmail(applymentPartnersDto.getEmail());
        applymentPartners.setTaxEmail(applymentPartnersDto.getTaxEmail());
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
        applymentPartnersResponseDto.setEmail(applymentPartners.getEmail());
        applymentPartnersResponseDto.setTaxEmail(applymentPartners.getTaxEmail());

        return applymentPartnersResponseDto;
    }

    @Override
    public List<ApplymentPartnersResponseDto> getAllApplymentPartners() {
        List<ApplymentPartners> applymentPartnersList = applymentPartnersRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        List<ApplymentPartnersResponseDto> applymentPartnersResponseDtoList = new ArrayList<>();

        for(ApplymentPartners applymentPartners : applymentPartnersList){ // 3
            ApplymentPartnersResponseDto dto = ApplymentPartnersResponseDto.builder()
                    .id(applymentPartners.getId())
                    .businessName(applymentPartners.getBusinessName())
                    .businessNum(applymentPartners.getBusinessNum())
                    .representative(applymentPartners.getRepresentative())
                    .businessAddress(applymentPartners.getBusinessAddress())
                    .serviceName(applymentPartners.getServiceName())
                    .siteAddress(applymentPartners.getSiteAddress())
                    .storeLocation(applymentPartners.getStoreLocation())
                    .category(applymentPartners.getCategory())
                    .dealMethod(applymentPartners.getDealMethod())
                    .phoneNum(applymentPartners.getPhoneNum())
                    .ggudaId(applymentPartners.getGgudaId())
                    .adminId(applymentPartners.getAdminId())
                    .etcContent(applymentPartners.getEtcContent())
                    .memberId(applymentPartners.getMember().getId())
                    .createdAt(applymentPartners.getCreatedAt())
                    .updatedAt(applymentPartners.getUpdatedAt())
                    .name(applymentPartners.getName())
                    .email(applymentPartners.getEmail())
                    .taxEmail(applymentPartners.getTaxEmail())
                    .build();

            applymentPartnersResponseDtoList.add(dto);
        }

        return applymentPartnersResponseDtoList;
    }
}
