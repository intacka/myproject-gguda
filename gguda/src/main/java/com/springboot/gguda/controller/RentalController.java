package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.RentalDto;
import com.springboot.gguda.data.dto.RentalResponseDto;
import com.springboot.gguda.data.dto.RentalStopReqDto;
import com.springboot.gguda.data.dto.RentalStopReqResponseDto;
import com.springboot.gguda.data.repository.CouponRepository;
import com.springboot.gguda.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {
    private final RentalService rentalService;
    private final RentalStopReqService rentalStopReqService;
    private final ProductService productService;

    @Autowired
    public RentalController(RentalService rentalService,
                         RentalStopReqService rentalStopReqService,
                            ProductService productService) {
        this.rentalService = rentalService;
        this.rentalStopReqService = rentalStopReqService;
        this.productService = productService;
    }


    // 렌탈하기 API
    @PostMapping(value = "/rental-pay")
    public RentalResponseDto createRental(@RequestBody RentalDto rentalDto) {
        RentalResponseDto rentalResponseDto = rentalService.createRental(rentalDto);
        productService.putState(rentalDto.getProductId()); // 재고변동,판매량증가,품절이면 상태전환
        return rentalResponseDto;
    }

    // 렌탈목록 조회 (회원별)
    @GetMapping(value = "/view-list")
    public List<RentalResponseDto> getRentalList(Long memberId) {
        List<RentalResponseDto> rentalResponseDtos = rentalService.getRentalList(memberId);

        return rentalResponseDtos;
    }

    // 렌탈목록조회(전체회원)
    @GetMapping(value = "/view-all")
    public List<RentalResponseDto> getAllRentalList() {
        List<RentalResponseDto> rentalResponseDtos = rentalService.getAllRentalList();

        return rentalResponseDtos;

    }


    // 렌탈목록 Detail 조회
    @GetMapping(value = "/view-detail")
    public RentalResponseDto getRentalDetail(Long rentalId) {
        RentalResponseDto rentalResponseDto = rentalService.getRentalDetail(rentalId);

        return rentalResponseDto;

    }

    // 렌탈목록 중지요청
    @PostMapping(value = "/rental-stop-req/request")
    public RentalStopReqResponseDto createRentalStopReq(@RequestBody RentalStopReqDto rentalStopReqDto) {
        RentalStopReqResponseDto rentalStopReqResponseDto = rentalStopReqService.createRentalStopReq(rentalStopReqDto);

        return rentalStopReqResponseDto;
    }

    // 렌탈목록 중지요청 조회(all)

    @GetMapping(value = "/rental-stop-req/allview")
    public List<RentalStopReqResponseDto> getAllRentalStopReqList() {
        List<RentalStopReqResponseDto> rentalStopReqResponseDtos = rentalStopReqService.getAllRentalStopReq();

        return rentalStopReqResponseDtos;

    }

}
