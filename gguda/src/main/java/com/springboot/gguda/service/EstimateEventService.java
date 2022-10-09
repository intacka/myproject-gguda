package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.apply.EstimateElecDto;
import com.springboot.gguda.data.dto.apply.EstimateElecResponseDto;
import com.springboot.gguda.data.dto.apply.EstimateEventDto;
import com.springboot.gguda.data.dto.apply.EstimateEventResponseDto;

import java.util.List;

public interface EstimateEventService {

    EstimateEventResponseDto saveEstimateEventDto(EstimateEventDto estimateEventDto);

    List<EstimateEventResponseDto> getAllEstimateEvent();

    EstimateEventResponseDto getEstimateEvent(Long id);

    EstimateEventResponseDto putEstimateEvent(Long id);
}
