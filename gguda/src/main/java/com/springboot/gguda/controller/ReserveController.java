package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.*;
import com.springboot.gguda.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reserve")
public class ReserveController {

    private final ReserveHistoryService reserveHistoryService;

    @Autowired
    public ReserveController(ReserveHistoryService reserveHistoryService) {
        this.reserveHistoryService = reserveHistoryService;
    }

    @GetMapping(value = "/view")   //         적립금내역 조회하기
    public List<ReserveHistoryResponseDto> getAllReserveHistory(Long memberId){
        List<ReserveHistoryResponseDto> ReserveHistoryList = reserveHistoryService.getAllReserveHistory(memberId);

        return ReserveHistoryList;
    }

    @PostMapping(value = "/change")      //   적립금 변동시키기 API
    public ReserveHistoryResponseDto createReserveHistory(ReserveHistoryDto reserveHistoryDto) {
        ReserveHistoryResponseDto reserveHistoryResponseDto = reserveHistoryService.createReserveHistory(reserveHistoryDto);

        return reserveHistoryResponseDto;
    }

}
