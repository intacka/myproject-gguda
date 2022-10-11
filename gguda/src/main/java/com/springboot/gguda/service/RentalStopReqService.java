package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.RentalDto;
import com.springboot.gguda.data.dto.RentalResponseDto;
import com.springboot.gguda.data.dto.RentalStopReqDto;
import com.springboot.gguda.data.dto.RentalStopReqResponseDto;

import java.util.List;

public interface RentalStopReqService {
    RentalStopReqResponseDto createRentalStopReq(RentalStopReqDto rentalStopReqDto);

    List<RentalStopReqResponseDto> getAllRentalStopReq();
}
