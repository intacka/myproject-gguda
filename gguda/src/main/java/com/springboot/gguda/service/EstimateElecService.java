package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.ProductDto;
import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.data.dto.apply.ApplymentPartnersResponseDto;
import com.springboot.gguda.data.dto.apply.EstimateElecDto;
import com.springboot.gguda.data.dto.apply.EstimateElecResponseDto;

import java.util.List;

public interface EstimateElecService {

    EstimateElecResponseDto saveEstimateElecDto(EstimateElecDto estimateElecDto);

    List<EstimateElecResponseDto> getAllEstimateElec();

    EstimateElecResponseDto getEstimateElec(Long id);

    EstimateElecResponseDto putEstimateElec(Long id);

    List<EstimateElecResponseDto> getAllEstimateElecByMemberId(Long memberId);
}
