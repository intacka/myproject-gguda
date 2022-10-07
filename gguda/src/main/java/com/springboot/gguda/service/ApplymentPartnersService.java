package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.apply.ApplymentPartnersDto;
import com.springboot.gguda.data.dto.apply.ApplymentPartnersResponseDto;
import com.springboot.gguda.data.dto.apply.EstimateElecDto;
import com.springboot.gguda.data.dto.apply.EstimateElecResponseDto;

import java.util.List;

public interface ApplymentPartnersService {

    ApplymentPartnersResponseDto saveApplymentPartnersDto(ApplymentPartnersDto applymentPartnersDto);

    List<ApplymentPartnersResponseDto> getAllApplymentPartners();

}
