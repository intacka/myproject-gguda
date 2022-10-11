package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.PurchaseDto;
import com.springboot.gguda.data.dto.RentalDto;
import com.springboot.gguda.data.dto.RentalResponseDto;
import com.springboot.gguda.result.PurchaseResult;

import java.util.List;

public interface RentalService {
    RentalResponseDto createRental(RentalDto rentalDto);

    List<RentalResponseDto> getRentalList(Long memberId);

    List<RentalResponseDto> getAllRentalList();

    RentalResponseDto getRentalDetail(Long rentalId);
}
