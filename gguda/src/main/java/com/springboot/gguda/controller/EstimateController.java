package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.apply.*;
import com.springboot.gguda.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estimate")
public class EstimateController {
    private final EstimateElecService estimateElecService;
    private final EstimateTVService estimateTVService;
    private final EstimateEventService estimateEventService;
    @Autowired
    public EstimateController(EstimateElecService estimateElecService,
                          EstimateTVService estimateTVService,
                          EstimateEventService estimateEventService) {
        this.estimateElecService = estimateElecService;
        this.estimateTVService = estimateTVService;
        this.estimateEventService = estimateEventService;
    }


    //          견적신청-전자기기신청 API -> 관리자DB로 넘어간다 (PC,모니터)
    @PostMapping(value = "/apply/elec")
    public ResponseEntity<EstimateElecResponseDto> applyElec(@RequestBody EstimateElecDto estimateElecDto) {
        EstimateElecResponseDto estimateElecResponseDto = estimateElecService.saveEstimateElecDto(estimateElecDto);

        return ResponseEntity.status(HttpStatus.OK).body(estimateElecResponseDto);
    }

    //          견적신청-전자기기신청 API -> 관리자DB로 넘어간다 (TV)
    @PostMapping(value = "/apply/tv")
    public ResponseEntity<EstimateTVResponseDto> applyTV(@RequestBody EstimateTVDto estimateTVDto) {
        EstimateTVResponseDto estimateTVResponseDto = estimateTVService.saveEstimateTVDto(estimateTVDto);

        return ResponseEntity.status(HttpStatus.OK).body(estimateTVResponseDto);
    }



    // 견적신청List확인(전자기기)

    @GetMapping(value = "/view-list/elec")           //  신청목록 쭉 보기
    public List<EstimateElecResponseDto> getAllEstimateElec() {
        List<EstimateElecResponseDto> all = estimateElecService.getAllEstimateElec();

        return all;
    }

    // 견적신청List확인(TV)

    @GetMapping(value = "/view-list/tv")           //  신청목록 쭉 보기
    public List<EstimateTVResponseDto> getAllEstimateTV() {
        List<EstimateTVResponseDto> all = estimateTVService.getAllEstimateTV();

        return all;
    }

    // 견적신청디테일보기 (전자기기)

    @GetMapping(value = "/view-detail/elec")
    public EstimateElecResponseDto getEstimateElec(Long id) {           //전자기기견적서 고유id로 찾기
        EstimateElecResponseDto estimateElecResponseDto = estimateElecService.getEstimateElec(id);

        return estimateElecResponseDto;
    }

    // 견적신청디테일보기 (TV)

    @GetMapping(value = "/view-detail/tv")
    public EstimateTVResponseDto getEstimateTV(Long id) {             //전자기기견적서 고유id로 찾기
        EstimateTVResponseDto estimateTVResponseDto = estimateTVService.getEstimateTV(id);

        return estimateTVResponseDto;
    }

    // 견적신청허가 (전자기기)
    @PutMapping(value = "/approval/elec")
    public EstimateElecResponseDto putEstimateElec(Long id) {
        EstimateElecResponseDto estimateElecResponseDto = estimateElecService.putEstimateElec(id);

        return estimateElecResponseDto;
    }

    // 견적신청허가 (TV)
    @PutMapping(value = "/approval/tv")
    public EstimateTVResponseDto putEstimateTV(Long id) {
        EstimateTVResponseDto estimateTVResponseDto = estimateTVService.putEstimateTV(id);

        return estimateTVResponseDto;
    }

    // 견적신청List확인(전자기기) - 사용자용

    @GetMapping(value = "/view-list/user/elec")           //  신청목록 쭉 보기
    public List<EstimateElecResponseDto> getAllEstimateElec(Long memberId) {
        List<EstimateElecResponseDto> all = estimateElecService.getAllEstimateElecByMemberId(memberId);

        return all;
    }

    // 견적신청List확인(TV) - 사용자용

    @GetMapping(value = "/view-list/user/tv")           //  신청목록 쭉 보기
    public List<EstimateTVResponseDto> getAllEstimateTV(Long memberId) {
        List<EstimateTVResponseDto> all = estimateTVService.getAllEstimateTVByMemberId(memberId);

        return all;
    }




}
