package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.ReserveHistoryDto;
import com.springboot.gguda.data.dto.ReserveHistoryResponseDto;

import java.util.List;

public interface ReserveHistoryService {

    List<ReserveHistoryResponseDto> getAllReserveHistory(Long memberId);

    ReserveHistoryResponseDto createReserveHistory(ReserveHistoryDto reserveHistoryDto);

    void createSigninReserveHistory(Long memberId);

    void createReviewReserveHistory(Long memberId);

    void createPurchaseReserveHistory(Integer reservePrice, Long memberId);
}
