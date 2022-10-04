package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.apply.EstimateElecDto;
import com.springboot.gguda.data.dto.apply.EstimateElecResponseDto;
import com.springboot.gguda.data.dto.apply.EstimateTVDto;
import com.springboot.gguda.data.dto.apply.EstimateTVResponseDto;

public interface EstimateTVService {

    EstimateTVResponseDto saveEstimateTVDto(EstimateTVDto estimateTVDto);

}
