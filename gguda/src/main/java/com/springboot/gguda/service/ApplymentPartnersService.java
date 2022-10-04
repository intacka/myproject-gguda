package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.apply.ApplymentPartnersDto;
import com.springboot.gguda.data.dto.apply.ApplymentPartnersResponseDto;
import com.springboot.gguda.data.dto.apply.EstimateElecDto;
import com.springboot.gguda.data.dto.apply.EstimateElecResponseDto;

public interface ApplymentPartnersService {

    ApplymentPartnersResponseDto saveApplymentPartnersDto(ApplymentPartnersDto applymentPartnersDto);

}
