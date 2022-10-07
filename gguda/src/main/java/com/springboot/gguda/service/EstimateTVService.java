package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.apply.*;

import java.util.List;

public interface EstimateTVService {

    EstimateTVResponseDto saveEstimateTVDto(EstimateTVDto estimateTVDto);

    List<EstimateTVResponseDto> getAllEstimateTV();

    EstimateTVResponseDto getEstimateTV(Long id);

}
