package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.apply.*;
import com.springboot.gguda.data.entity.apply.ApplymentPartners;
import com.springboot.gguda.data.entity.apply.EstimateElec;
import com.springboot.gguda.data.entity.apply.EstimateTV;
import com.springboot.gguda.data.repository.EstimateElecRepository;
import com.springboot.gguda.data.repository.EstimateTVRepository;
import com.springboot.gguda.data.repository.MemberRepository;
import com.springboot.gguda.service.EstimateElecService;
import com.springboot.gguda.service.EstimateTVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstimateTVServiceImpl implements EstimateTVService {


    private final MemberRepository memberRepository;
    private final EstimateTVRepository estimateTVRepository;

    @Autowired
    public EstimateTVServiceImpl(MemberRepository memberRepository, EstimateTVRepository estimateTVRepository) {
        this.memberRepository = memberRepository;
        this.estimateTVRepository = estimateTVRepository;
    }


    @Override
    public EstimateTVResponseDto saveEstimateTVDto(EstimateTVDto estimateTVDto) {
        EstimateTV estimateTV = new EstimateTV();
        estimateTV.setVolumn(estimateTVDto.getVolumn());
        estimateTV.setTvSize(estimateTVDto.getTvSize());
        estimateTV.setInstallType(estimateTVDto.getInstallType());
        estimateTV.setConnectSort(estimateTVDto.getConnectSort());
        estimateTV.setLink(estimateTVDto.getLink());
        estimateTV.setEventContent(estimateTVDto.getEventContent());
        estimateTV.setPurpose(estimateTVDto.getPurpose());
        estimateTV.setInstallDate(estimateTVDto.getInstallDate());
        estimateTV.setCollectDate(estimateTVDto.getCollectDate());
        estimateTV.setAddress(estimateTVDto.getAddress());
        estimateTV.setInstallYn(estimateTVDto.getInstallYn());
        estimateTV.setElevatorYn(estimateTVDto.getElevatorYn());
        estimateTV.setManagerName(estimateTVDto.getManagerName());
        estimateTV.setManagerContact(estimateTVDto.getManagerContact());
        estimateTV.setManagerEmail(estimateTVDto.getManagerEmail());
        estimateTV.setTaxEmail(estimateTVDto.getTaxEmail());
        estimateTV.setIsConfirmed(estimateTVDto.getIsConfirmed());
        estimateTV.setMember(memberRepository.findById(estimateTVDto.getMemberId()).get());

        estimateTVRepository.save(estimateTV);

        EstimateTVResponseDto estimateTVResponseDto = new EstimateTVResponseDto();
        estimateTVResponseDto.setId(estimateTV.getId());
        estimateTVResponseDto.setVolumn(estimateTV.getVolumn());
        estimateTVResponseDto.setTvSize(estimateTV.getTvSize());
        estimateTVResponseDto.setInstallType(estimateTV.getInstallType());
        estimateTVResponseDto.setConnectSort(estimateTV.getConnectSort());
        estimateTVResponseDto.setLink(estimateTV.getLink());
        estimateTVResponseDto.setEventContent(estimateTV.getEventContent());
        estimateTVResponseDto.setPurpose(estimateTV.getPurpose());
        estimateTVResponseDto.setInstallDate(estimateTV.getInstallDate());
        estimateTVResponseDto.setCollectDate(estimateTV.getCollectDate());
        estimateTVResponseDto.setAddress(estimateTV.getAddress());
        estimateTVResponseDto.setInstallYn(estimateTV.getInstallYn());
        estimateTVResponseDto.setElevatorYn(estimateTV.getElevatorYn());
        estimateTVResponseDto.setManagerName(estimateTV.getManagerName());
        estimateTVResponseDto.setManagerContact(estimateTV.getManagerContact());
        estimateTVResponseDto.setManagerEmail(estimateTV.getManagerEmail());
        estimateTVResponseDto.setTaxEmail(estimateTV.getTaxEmail());
        estimateTVResponseDto.setIsConfirmed(estimateTV.getIsConfirmed());
        estimateTVResponseDto.setMemberId(estimateTV.getMember().getId());
        estimateTVResponseDto.setCreatedAt(estimateTV.getCreatedAt());
        estimateTVResponseDto.setUpdatedAt(estimateTV.getUpdatedAt());

        return estimateTVResponseDto;
    }

    @Override
    public List<EstimateTVResponseDto> getAllEstimateTV() {
        List<EstimateTV> estimateTVs = estimateTVRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        List<EstimateTVResponseDto> estimateTVResponseDtoList = new ArrayList<>();

        for(EstimateTV estimateTV : estimateTVs){ // 3
            EstimateTVResponseDto dto = EstimateTVResponseDto.builder()
                    .id(estimateTV.getId())
                    .volumn(estimateTV.getVolumn())
                    .tvSize(estimateTV.getTvSize())
                    .installType(estimateTV.getInstallType())
                    .connectSort(estimateTV.getConnectSort())
                    .link(estimateTV.getLink())
                    .eventContent(estimateTV.getEventContent())
                    .purpose(estimateTV.getPurpose())
                    .installDate(estimateTV.getInstallDate())
                    .collectDate(estimateTV.getCollectDate())
                    .address(estimateTV.getAddress())
                    .installYn(estimateTV.getInstallYn())
                    .elevatorYn(estimateTV.getElevatorYn())
                    .managerName(estimateTV.getManagerName())
                    .managerContact(estimateTV.getManagerContact())
                    .managerEmail(estimateTV.getManagerEmail())
                    .taxEmail(estimateTV.getTaxEmail())
                    .isConfirmed(estimateTV.getIsConfirmed())
                    .memberId(estimateTV.getMember().getId())
                    .createdAt(estimateTV.getCreatedAt())
                    .updatedAt(estimateTV.getUpdatedAt())
                    .build();

            estimateTVResponseDtoList.add(dto);
        }

        return estimateTVResponseDtoList;
    }

    @Override
    public EstimateTVResponseDto getEstimateTV(Long id) {
        EstimateTV estimateTV = estimateTVRepository.getById(id);

        EstimateTVResponseDto estimateTVResponseDto = new EstimateTVResponseDto();
        estimateTVResponseDto.setId(estimateTV.getId());
        estimateTVResponseDto.setVolumn(estimateTV.getVolumn());
        estimateTVResponseDto.setTvSize(estimateTV.getTvSize());
        estimateTVResponseDto.setInstallType(estimateTV.getInstallType());
        estimateTVResponseDto.setConnectSort(estimateTV.getConnectSort());
        estimateTVResponseDto.setLink(estimateTV.getLink());
        estimateTVResponseDto.setEventContent(estimateTV.getEventContent());
        estimateTVResponseDto.setPurpose(estimateTV.getPurpose());
        estimateTVResponseDto.setInstallDate(estimateTV.getInstallDate());
        estimateTVResponseDto.setCollectDate(estimateTV.getCollectDate());
        estimateTVResponseDto.setAddress(estimateTV.getAddress());
        estimateTVResponseDto.setInstallYn(estimateTV.getInstallYn());
        estimateTVResponseDto.setElevatorYn(estimateTV.getElevatorYn());
        estimateTVResponseDto.setManagerName(estimateTV.getManagerName());
        estimateTVResponseDto.setManagerContact(estimateTV.getManagerContact());
        estimateTVResponseDto.setManagerEmail(estimateTV.getManagerEmail());
        estimateTVResponseDto.setTaxEmail(estimateTV.getTaxEmail());
        estimateTVResponseDto.setIsConfirmed(estimateTV.getIsConfirmed());
        estimateTVResponseDto.setMemberId(estimateTV.getMember().getId());
        estimateTVResponseDto.setCreatedAt(estimateTV.getCreatedAt());
        estimateTVResponseDto.setUpdatedAt(estimateTV.getUpdatedAt());

        return estimateTVResponseDto;
    }

    @Override
    public EstimateTVResponseDto putEstimateTV(Long id) {
        EstimateTV estimateTV = estimateTVRepository.getById(id);
        estimateTV.setIsConfirmed(1);

        estimateTVRepository.save(estimateTV);

        EstimateTVResponseDto estimateTVResponseDto = new EstimateTVResponseDto();
        estimateTVResponseDto.setId(estimateTV.getId());
        estimateTVResponseDto.setVolumn(estimateTV.getVolumn());
        estimateTVResponseDto.setTvSize(estimateTV.getTvSize());
        estimateTVResponseDto.setInstallType(estimateTV.getInstallType());
        estimateTVResponseDto.setConnectSort(estimateTV.getConnectSort());
        estimateTVResponseDto.setLink(estimateTV.getLink());
        estimateTVResponseDto.setEventContent(estimateTV.getEventContent());
        estimateTVResponseDto.setPurpose(estimateTV.getPurpose());
        estimateTVResponseDto.setInstallDate(estimateTV.getInstallDate());
        estimateTVResponseDto.setCollectDate(estimateTV.getCollectDate());
        estimateTVResponseDto.setAddress(estimateTV.getAddress());
        estimateTVResponseDto.setInstallYn(estimateTV.getInstallYn());
        estimateTVResponseDto.setElevatorYn(estimateTV.getElevatorYn());
        estimateTVResponseDto.setManagerName(estimateTV.getManagerName());
        estimateTVResponseDto.setManagerContact(estimateTV.getManagerContact());
        estimateTVResponseDto.setManagerEmail(estimateTV.getManagerEmail());
        estimateTVResponseDto.setTaxEmail(estimateTV.getTaxEmail());
        estimateTVResponseDto.setIsConfirmed(estimateTV.getIsConfirmed());
        estimateTVResponseDto.setMemberId(estimateTV.getMember().getId());
        estimateTVResponseDto.setCreatedAt(estimateTV.getCreatedAt());
        estimateTVResponseDto.setUpdatedAt(estimateTV.getUpdatedAt());

        return estimateTVResponseDto;
    }

    @Override
    public List<EstimateTVResponseDto> getAllEstimateTVByMemberId(Long memberId) {
        List<EstimateTV> estimateTVs = estimateTVRepository.findAllByMemberIdOrderByCreatedAtDesc(memberId);

        List<EstimateTVResponseDto> estimateTVResponseDtoList = new ArrayList<>();

        for(EstimateTV estimateTV : estimateTVs){ // 3
            EstimateTVResponseDto dto = EstimateTVResponseDto.builder()
                    .id(estimateTV.getId())
                    .volumn(estimateTV.getVolumn())
                    .tvSize(estimateTV.getTvSize())
                    .installType(estimateTV.getInstallType())
                    .connectSort(estimateTV.getConnectSort())
                    .link(estimateTV.getLink())
                    .eventContent(estimateTV.getEventContent())
                    .purpose(estimateTV.getPurpose())
                    .installDate(estimateTV.getInstallDate())
                    .collectDate(estimateTV.getCollectDate())
                    .address(estimateTV.getAddress())
                    .installYn(estimateTV.getInstallYn())
                    .elevatorYn(estimateTV.getElevatorYn())
                    .managerName(estimateTV.getManagerName())
                    .managerContact(estimateTV.getManagerContact())
                    .managerEmail(estimateTV.getManagerEmail())
                    .taxEmail(estimateTV.getTaxEmail())
                    .isConfirmed(estimateTV.getIsConfirmed())
                    .memberId(estimateTV.getMember().getId())
                    .createdAt(estimateTV.getCreatedAt())
                    .updatedAt(estimateTV.getUpdatedAt())
                    .build();

            estimateTVResponseDtoList.add(dto);
        }

        return estimateTVResponseDtoList;
    }
}
