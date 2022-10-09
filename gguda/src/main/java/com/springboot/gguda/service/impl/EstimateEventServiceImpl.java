package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.apply.*;
import com.springboot.gguda.data.entity.EventProduct;
import com.springboot.gguda.data.entity.Product;
import com.springboot.gguda.data.entity.apply.ApplymentPartners;
import com.springboot.gguda.data.entity.apply.EstimateElec;
import com.springboot.gguda.data.entity.apply.EstimateEvent;
import com.springboot.gguda.data.entity.apply.EstimateTV;
import com.springboot.gguda.data.repository.*;
import com.springboot.gguda.service.EstimateEventService;
import com.springboot.gguda.service.EstimateTVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstimateEventServiceImpl implements EstimateEventService {


    private final MemberRepository memberRepository;
    private final EstimateEventRepository estimateEventRepository;
    private final EventProductRepository eventProductRepository;

    @Autowired
    public EstimateEventServiceImpl(MemberRepository memberRepository, EstimateEventRepository estimateEventRepository,
                                    EventProductRepository eventProductRepository) {
        this.memberRepository = memberRepository;
        this.estimateEventRepository = estimateEventRepository;
        this.eventProductRepository = eventProductRepository;
    }


    @Override
    public EstimateEventResponseDto saveEstimateEventDto(EstimateEventDto estimateEventDto) {
        EstimateEvent estimateEvent = new EstimateEvent();
        //
        List<Long> pIds = estimateEventDto.getEventProductsId();
        List<EventProduct> eventProducts = new ArrayList<>();
        for (Long pId : pIds) {
            eventProducts.add(eventProductRepository.getById(pId));
        }
        estimateEvent.setEventProducts(eventProducts);
        ///////
        estimateEvent.setEtcReq(estimateEventDto.getEtcReq());
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
        estimateEvent.setIsConfirmed(estimateEventDto.getIsConfirmed());
        estimateEvent.setMember(memberRepository.findById(estimateEventDto.getMemberId()).get());

        estimateEventRepository.save(estimateEvent);

        EstimateEventResponseDto estimateEventResponseDto = new EstimateEventResponseDto();
        estimateEventResponseDto.setId(estimateEvent.getId());
        estimateEventResponseDto.setEventProductsId(pIds);
        estimateEventResponseDto.setEtcReq(estimateEvent.getEtcReq());
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
        estimateEventResponseDto.setIsConfirmed(estimateEvent.getIsConfirmed());
        estimateEventResponseDto.setMemberId(estimateEvent.getMember().getId());
        estimateEventResponseDto.setCreatedAt(estimateEvent.getCreatedAt());
        estimateEventResponseDto.setUpdatedAt(estimateEvent.getUpdatedAt());

        return estimateEventResponseDto;
    }

    @Override
    public List<EstimateEventResponseDto> getAllEstimateEvent() {
        List<EstimateEvent> estimateEvents = estimateEventRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        List<EstimateEventResponseDto> estimateEventResponseDtoList = new ArrayList<>();

        for(EstimateEvent estimateEvent : estimateEvents){ // 3


            List<EventProduct> eventProducts = estimateEvent.getEventProducts();
            List<Long> pIds = new ArrayList<>();
            for (EventProduct eventProduct : eventProducts) {
                pIds.add(eventProduct.getId());
            }

            EstimateEventResponseDto dto = EstimateEventResponseDto.builder()
                    .id(estimateEvent.getId())
                    .eventProductsId(pIds)
                    .etcReq(estimateEvent.getEtcReq())
                    .link(estimateEvent.getLink())
                    .eventContent(estimateEvent.getEventContent())
                    .purpose(estimateEvent.getPurpose())
                    .installDate(estimateEvent.getInstallDate())
                    .collectDate(estimateEvent.getCollectDate())
                    .address(estimateEvent.getAddress())
                    .installYn(estimateEvent.getInstallYn())
                    .elevatorYn(estimateEvent.getElevatorYn())
                    .managerName(estimateEvent.getManagerName())
                    .managerContact(estimateEvent.getManagerContact())
                    .managerEmail(estimateEvent.getManagerEmail())
                    .taxEmail(estimateEvent.getTaxEmail())
                    .isConfirmed(estimateEvent.getIsConfirmed())
                    .memberId(estimateEvent.getMember().getId())
                    .createdAt(estimateEvent.getCreatedAt())
                    .updatedAt(estimateEvent.getUpdatedAt())
                    .build();

            estimateEventResponseDtoList.add(dto);
        }

        return estimateEventResponseDtoList;
    }

    @Override
    public EstimateEventResponseDto getEstimateEvent(Long id) {
        EstimateEvent estimateEvent = estimateEventRepository.getById(id);

        EstimateEventResponseDto estimateEventResponseDto = new EstimateEventResponseDto();
        estimateEventResponseDto.setId(estimateEvent.getId());

        List<EventProduct> eventProducts = estimateEvent.getEventProducts();
        List<Long> pIds = new ArrayList<>();
        for (EventProduct eventProduct : eventProducts) {
            pIds.add(eventProduct.getId());
        }
        estimateEventResponseDto.setEventProductsId(pIds);

        estimateEventResponseDto.setEtcReq(estimateEvent.getEtcReq());
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
        estimateEventResponseDto.setIsConfirmed(estimateEvent.getIsConfirmed());
        estimateEventResponseDto.setMemberId(estimateEvent.getMember().getId());
        estimateEventResponseDto.setCreatedAt(estimateEvent.getCreatedAt());
        estimateEventResponseDto.setUpdatedAt(estimateEvent.getUpdatedAt());

        return estimateEventResponseDto;
    }

    @Override
    public EstimateEventResponseDto putEstimateEvent(Long id) {
        EstimateEvent estimateEvent = estimateEventRepository.getById(id);
        estimateEvent.setIsConfirmed(1);

        estimateEventRepository.save(estimateEvent);

        EstimateEventResponseDto estimateEventResponseDto = new EstimateEventResponseDto();
        estimateEventResponseDto.setId(estimateEvent.getId());

        List<EventProduct> eventProducts = estimateEvent.getEventProducts();
        List<Long> pIds = new ArrayList<>();
        for (EventProduct eventProduct : eventProducts) {
            pIds.add(eventProduct.getId());
        }
        estimateEventResponseDto.setEventProductsId(pIds);

        estimateEventResponseDto.setEtcReq(estimateEvent.getEtcReq());
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
        estimateEventResponseDto.setIsConfirmed(estimateEvent.getIsConfirmed());
        estimateEventResponseDto.setMemberId(estimateEvent.getMember().getId());
        estimateEventResponseDto.setCreatedAt(estimateEvent.getCreatedAt());
        estimateEventResponseDto.setUpdatedAt(estimateEvent.getUpdatedAt());

        return estimateEventResponseDto;
    }


}
