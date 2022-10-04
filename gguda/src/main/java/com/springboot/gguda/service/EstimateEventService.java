package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.apply.EstimateElecDto;
import com.springboot.gguda.data.dto.apply.EstimateElecResponseDto;
import com.springboot.gguda.data.dto.apply.EstimateEventDto;
import com.springboot.gguda.data.dto.apply.EstimateEventResponseDto;

public interface EstimateEventService {

    EstimateEventResponseDto saveEstimateEventDto(EstimateEventDto estimateEventDto);

}
