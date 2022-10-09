package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.apply.ApplymentPartnersResponseDto;
import com.springboot.gguda.data.dto.apply.EstimateElecDto;
import com.springboot.gguda.data.dto.apply.EstimateElecResponseDto;
import com.springboot.gguda.data.entity.apply.ApplymentPartners;
import com.springboot.gguda.data.entity.apply.EstimateElec;
import com.springboot.gguda.data.repository.EstimateElecRepository;
import com.springboot.gguda.data.repository.MemberRepository;
import com.springboot.gguda.service.EstimateElecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstimateElecServiceImpl implements EstimateElecService {


    private final MemberRepository memberRepository;
    private final EstimateElecRepository estimateElecRepository;

    @Autowired
    public EstimateElecServiceImpl(MemberRepository memberRepository, EstimateElecRepository estimateElecRepository) {
        this.memberRepository = memberRepository;
        this.estimateElecRepository = estimateElecRepository;
    }


    @Override
    public EstimateElecResponseDto saveEstimateElecDto(EstimateElecDto estimateElecDto) {
        EstimateElec estimateElec = new EstimateElec();
        estimateElec.setMonitorSize(estimateElecDto.getMonitorSize());
        estimateElec.setCpu(estimateElecDto.getCpu());
        estimateElec.setRam(estimateElecDto.getRam());
        estimateElec.setSsd(estimateElecDto.getSsd());
        estimateElec.setOs(estimateElecDto.getOs());
        estimateElec.setOthers(estimateElecDto.getOthers());
        estimateElec.setInternetSystem(estimateElecDto.getInternetSystem());
        estimateElec.setAddProgram(estimateElecDto.getAddProgram());
        estimateElec.setLink(estimateElecDto.getLink());
        estimateElec.setEventContent(estimateElecDto.getEventContent());
        estimateElec.setPurpose(estimateElecDto.getPurpose());
        estimateElec.setInstallDate(estimateElecDto.getInstallDate());
        estimateElec.setCollectDate(estimateElecDto.getCollectDate());
        estimateElec.setAddress(estimateElecDto.getAddress());
        estimateElec.setInstallYn(estimateElecDto.getInstallYn());
        estimateElec.setElevatorYn(estimateElecDto.getElevatorYn());
        estimateElec.setManagerName(estimateElecDto.getManagerName());
        estimateElec.setManagerContact(estimateElecDto.getManagerContact());
        estimateElec.setManagerEmail(estimateElecDto.getManagerEmail());
        estimateElec.setTaxEmail(estimateElecDto.getTaxEmail());
        estimateElec.setVolumn(estimateElecDto.getVolumn());
        estimateElec.setIsConfirmed(estimateElecDto.getIsConfirmed());
        estimateElec.setMember(memberRepository.findById(estimateElecDto.getMemberId()).get());

        estimateElecRepository.save(estimateElec);

        EstimateElecResponseDto estimateElecResponseDto = new EstimateElecResponseDto();
        estimateElecResponseDto.setId(estimateElec.getId());
        estimateElecResponseDto.setMonitorSize(estimateElec.getMonitorSize());
        estimateElecResponseDto.setCpu(estimateElec.getCpu());
        estimateElecResponseDto.setRam(estimateElec.getRam());
        estimateElecResponseDto.setSsd(estimateElec.getSsd());
        estimateElecResponseDto.setOs(estimateElec.getOs());
        estimateElecResponseDto.setOthers(estimateElec.getOthers());
        estimateElecResponseDto.setInternetSystem(estimateElec.getInternetSystem());
        estimateElecResponseDto.setAddProgram(estimateElec.getAddProgram());
        estimateElecResponseDto.setLink(estimateElec.getLink());
        estimateElecResponseDto.setEventContent(estimateElec.getEventContent());
        estimateElecResponseDto.setPurpose(estimateElec.getPurpose());
        estimateElecResponseDto.setInstallDate(estimateElec.getInstallDate());
        estimateElecResponseDto.setCollectDate(estimateElec.getCollectDate());
        estimateElecResponseDto.setAddress(estimateElec.getAddress());
        estimateElecResponseDto.setInstallYn(estimateElec.getInstallYn());
        estimateElecResponseDto.setElevatorYn(estimateElec.getElevatorYn());
        estimateElecResponseDto.setManagerName(estimateElec.getManagerName());
        estimateElecResponseDto.setManagerContact(estimateElec.getManagerContact());
        estimateElecResponseDto.setManagerEmail(estimateElec.getManagerEmail());
        estimateElecResponseDto.setTaxEmail(estimateElec.getTaxEmail());
        estimateElecResponseDto.setVolumn(estimateElec.getVolumn());
        estimateElecResponseDto.setIsConfirmed(estimateElec.getIsConfirmed());
        estimateElecResponseDto.setMemberId(estimateElec.getMember().getId());
        estimateElecResponseDto.setCreatedAt(estimateElec.getCreatedAt());
        estimateElecResponseDto.setUpdatedAt(estimateElec.getUpdatedAt());

        return estimateElecResponseDto;
    }

    @Override
    public List<EstimateElecResponseDto> getAllEstimateElec() {
        List<EstimateElec> estimateElecs = estimateElecRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        List<EstimateElecResponseDto> estimateElecResponseDtoList = new ArrayList<>();

        for(EstimateElec estimateElec : estimateElecs){ // 3
            EstimateElecResponseDto dto = EstimateElecResponseDto.builder()
                    .id(estimateElec.getId())
                    .volumn(estimateElec.getVolumn())
                    .monitorSize(estimateElec.getMonitorSize())
                    .cpu(estimateElec.getCpu())
                    .ram(estimateElec.getRam())
                    .ssd(estimateElec.getSsd())
                    .os(estimateElec.getOs())
                    .others(estimateElec.getOthers())
                    .internetSystem(estimateElec.getInternetSystem())
                    .addProgram(estimateElec.getAddProgram())
                    .link(estimateElec.getLink())
                    .eventContent(estimateElec.getEventContent())
                    .purpose(estimateElec.getPurpose())
                    .installDate(estimateElec.getInstallDate())
                    .collectDate(estimateElec.getCollectDate())
                    .address(estimateElec.getAddress())
                    .installYn(estimateElec.getInstallYn())
                    .managerName(estimateElec.getManagerName())
                    .managerEmail(estimateElec.getManagerEmail())
                    .managerContact(estimateElec.getManagerContact())
                    .taxEmail(estimateElec.getTaxEmail())
                    .isConfirmed(estimateElec.getIsConfirmed())
                    .memberId(estimateElec.getMember().getId())
                    .createdAt(estimateElec.getCreatedAt())
                    .updatedAt(estimateElec.getUpdatedAt())
                    .build();

            estimateElecResponseDtoList.add(dto);
        }

        return estimateElecResponseDtoList;
    }

    @Override
    public EstimateElecResponseDto getEstimateElec(Long id) {
        EstimateElec estimateElec = estimateElecRepository.getById(id);

        EstimateElecResponseDto estimateElecResponseDto = new EstimateElecResponseDto();
        estimateElecResponseDto.setId(estimateElec.getId());
        estimateElecResponseDto.setMonitorSize(estimateElec.getMonitorSize());
        estimateElecResponseDto.setCpu(estimateElec.getCpu());
        estimateElecResponseDto.setRam(estimateElec.getRam());
        estimateElecResponseDto.setSsd(estimateElec.getSsd());
        estimateElecResponseDto.setOs(estimateElec.getOs());
        estimateElecResponseDto.setOthers(estimateElec.getOthers());
        estimateElecResponseDto.setInternetSystem(estimateElec.getInternetSystem());
        estimateElecResponseDto.setAddProgram(estimateElec.getAddProgram());
        estimateElecResponseDto.setLink(estimateElec.getLink());
        estimateElecResponseDto.setEventContent(estimateElec.getEventContent());
        estimateElecResponseDto.setPurpose(estimateElec.getPurpose());
        estimateElecResponseDto.setInstallDate(estimateElec.getInstallDate());
        estimateElecResponseDto.setCollectDate(estimateElec.getCollectDate());
        estimateElecResponseDto.setAddress(estimateElec.getAddress());
        estimateElecResponseDto.setInstallYn(estimateElec.getInstallYn());
        estimateElecResponseDto.setElevatorYn(estimateElec.getElevatorYn());
        estimateElecResponseDto.setManagerName(estimateElec.getManagerName());
        estimateElecResponseDto.setManagerContact(estimateElec.getManagerContact());
        estimateElecResponseDto.setManagerEmail(estimateElec.getManagerEmail());
        estimateElecResponseDto.setTaxEmail(estimateElec.getTaxEmail());
        estimateElecResponseDto.setVolumn(estimateElec.getVolumn());
        estimateElecResponseDto.setIsConfirmed(estimateElec.getIsConfirmed());
        estimateElecResponseDto.setMemberId(estimateElec.getMember().getId());
        estimateElecResponseDto.setCreatedAt(estimateElec.getCreatedAt());
        estimateElecResponseDto.setUpdatedAt(estimateElec.getUpdatedAt());


        return estimateElecResponseDto;
    }

    @Override
    public EstimateElecResponseDto putEstimateElec(Long id) {
        EstimateElec estimateElec = estimateElecRepository.getById(id);
        estimateElec.setIsConfirmed(1);

        estimateElecRepository.save(estimateElec);

        EstimateElecResponseDto estimateElecResponseDto = new EstimateElecResponseDto();
        estimateElecResponseDto.setId(estimateElec.getId());
        estimateElecResponseDto.setMonitorSize(estimateElec.getMonitorSize());
        estimateElecResponseDto.setCpu(estimateElec.getCpu());
        estimateElecResponseDto.setRam(estimateElec.getRam());
        estimateElecResponseDto.setSsd(estimateElec.getSsd());
        estimateElecResponseDto.setOs(estimateElec.getOs());
        estimateElecResponseDto.setOthers(estimateElec.getOthers());
        estimateElecResponseDto.setInternetSystem(estimateElec.getInternetSystem());
        estimateElecResponseDto.setAddProgram(estimateElec.getAddProgram());
        estimateElecResponseDto.setLink(estimateElec.getLink());
        estimateElecResponseDto.setEventContent(estimateElec.getEventContent());
        estimateElecResponseDto.setPurpose(estimateElec.getPurpose());
        estimateElecResponseDto.setInstallDate(estimateElec.getInstallDate());
        estimateElecResponseDto.setCollectDate(estimateElec.getCollectDate());
        estimateElecResponseDto.setAddress(estimateElec.getAddress());
        estimateElecResponseDto.setInstallYn(estimateElec.getInstallYn());
        estimateElecResponseDto.setElevatorYn(estimateElec.getElevatorYn());
        estimateElecResponseDto.setManagerName(estimateElec.getManagerName());
        estimateElecResponseDto.setManagerContact(estimateElec.getManagerContact());
        estimateElecResponseDto.setManagerEmail(estimateElec.getManagerEmail());
        estimateElecResponseDto.setTaxEmail(estimateElec.getTaxEmail());
        estimateElecResponseDto.setVolumn(estimateElec.getVolumn());
        estimateElecResponseDto.setIsConfirmed(estimateElec.getIsConfirmed());
        estimateElecResponseDto.setMemberId(estimateElec.getMember().getId());
        estimateElecResponseDto.setCreatedAt(estimateElec.getCreatedAt());
        estimateElecResponseDto.setUpdatedAt(estimateElec.getUpdatedAt());


        return estimateElecResponseDto;
    }

    @Override
    public List<EstimateElecResponseDto> getAllEstimateElecByMemberId(Long memberId) {
        List<EstimateElec> estimateElecs = estimateElecRepository.findAllByMemberIdOrderByCreatedAtDesc(memberId);

        List<EstimateElecResponseDto> estimateElecResponseDtoList = new ArrayList<>();

        for(EstimateElec estimateElec : estimateElecs){ // 3
            EstimateElecResponseDto dto = EstimateElecResponseDto.builder()
                    .id(estimateElec.getId())
                    .volumn(estimateElec.getVolumn())
                    .monitorSize(estimateElec.getMonitorSize())
                    .cpu(estimateElec.getCpu())
                    .ram(estimateElec.getRam())
                    .ssd(estimateElec.getSsd())
                    .os(estimateElec.getOs())
                    .others(estimateElec.getOthers())
                    .internetSystem(estimateElec.getInternetSystem())
                    .addProgram(estimateElec.getAddProgram())
                    .link(estimateElec.getLink())
                    .eventContent(estimateElec.getEventContent())
                    .purpose(estimateElec.getPurpose())
                    .installDate(estimateElec.getInstallDate())
                    .collectDate(estimateElec.getCollectDate())
                    .address(estimateElec.getAddress())
                    .installYn(estimateElec.getInstallYn())
                    .managerName(estimateElec.getManagerName())
                    .managerEmail(estimateElec.getManagerEmail())
                    .managerContact(estimateElec.getManagerContact())
                    .taxEmail(estimateElec.getTaxEmail())
                    .isConfirmed(estimateElec.getIsConfirmed())
                    .memberId(estimateElec.getMember().getId())
                    .createdAt(estimateElec.getCreatedAt())
                    .updatedAt(estimateElec.getUpdatedAt())
                    .build();

            estimateElecResponseDtoList.add(dto);
        }

        return estimateElecResponseDtoList;
    }
}
