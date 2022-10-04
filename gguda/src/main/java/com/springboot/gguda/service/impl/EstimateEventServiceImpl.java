package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.apply.EstimateEventDto;
import com.springboot.gguda.data.dto.apply.EstimateEventResponseDto;
import com.springboot.gguda.data.dto.apply.EstimateTVDto;
import com.springboot.gguda.data.dto.apply.EstimateTVResponseDto;
import com.springboot.gguda.data.entity.apply.EstimateEvent;
import com.springboot.gguda.data.entity.apply.EstimateTV;
import com.springboot.gguda.data.repository.EstimateEventRepository;
import com.springboot.gguda.data.repository.EstimateTVRepository;
import com.springboot.gguda.data.repository.MemberRepository;
import com.springboot.gguda.service.EstimateEventService;
import com.springboot.gguda.service.EstimateTVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstimateEventServiceImpl implements EstimateEventService {


    private final MemberRepository memberRepository;
    private final EstimateEventRepository estimateEventRepository;

    @Autowired
    public EstimateEventServiceImpl(MemberRepository memberRepository, EstimateEventRepository estimateEventRepository) {
        this.memberRepository = memberRepository;
        this.estimateEventRepository = estimateEventRepository;
    }


    @Override
    public EstimateEventResponseDto saveEstimateEventDto(EstimateEventDto estimateEventDto) {
        EstimateEvent estimateEvent = new EstimateEvent();
        estimateEvent.setVolumn(estimateEventDto.getVolumn());
        estimateEvent.setLink(estimateEventDto.getLink());
        estimateEvent.setEventContent(estimateEventDto.getEventContent());
        estimateEvent.setPurpose(estimateEventDto.getPurpose());
        estimateEvent.setInstallDate(estimateEventDto.getInstallDate());
        estimateEvent.setCollectDate(estimateEventDto.getCollectDate());
        estimateEvent.setAddress(estimateEventDto.getAddress());
        estimateEvent.setInstallYn(estimateEventDto.getInstallYn());
        estimateEvent.setElevatorYn(estimateEventDto.getElevatorYn());
        estimateEvent.setManagerName(estimateEventDto.getManagerName());
        estimateEvent.setManagerContact(estimateEventDto.getManagerContact());
        estimateEvent.setManagerEmail(estimateEventDto.getManagerEmail());
        estimateEvent.setTaxEmail(estimateEventDto.getTaxEmail());
        estimateEvent.setMember(memberRepository.findById(estimateEventDto.getMemberId()).get());

        estimateEventRepository.save(estimateEvent);

        EstimateEventResponseDto estimateEventResponseDto = new EstimateEventResponseDto();
        estimateEventResponseDto.setId(estimateEvent.getId());
        estimateEventResponseDto.setVolumn(estimateEvent.getVolumn());
        estimateEventResponseDto.setLink(estimateEvent.getLink());
        estimateEventResponseDto.setEventContent(estimateEvent.getEventContent());
        estimateEventResponseDto.setPurpose(estimateEvent.getPurpose());
        estimateEventResponseDto.setInstallDate(estimateEvent.getInstallDate());
        estimateEventResponseDto.setCollectDate(estimateEvent.getCollectDate());
        estimateEventResponseDto.setAddress(estimateEvent.getAddress());
        estimateEventResponseDto.setInstallYn(estimateEvent.getInstallYn());
        estimateEventResponseDto.setElevatorYn(estimateEvent.getElevatorYn());
        estimateEventResponseDto.setManagerName(estimateEvent.getManagerName());
        estimateEventResponseDto.setManagerContact(estimateEvent.getManagerContact());
        estimateEventResponseDto.setManagerEmail(estimateEvent.getManagerEmail());
        estimateEventResponseDto.setTaxEmail(estimateEvent.getTaxEmail());
        estimateEventResponseDto.setMemberId(estimateEvent.getMember().getId());
        estimateEventResponseDto.setCreatedAt(estimateEvent.getCreatedAt());
        estimateEventResponseDto.setUpdatedAt(estimateEvent.getUpdatedAt());

        return estimateEventResponseDto;
    }
}
