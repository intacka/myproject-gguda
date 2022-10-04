package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.ProductDto;
import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.data.dto.apply.EstimateElecDto;
import com.springboot.gguda.data.dto.apply.EstimateElecResponseDto;

public interface EstimateElecService {

    EstimateElecResponseDto saveEstimateElecDto(EstimateElecDto estimateElecDto);

}
