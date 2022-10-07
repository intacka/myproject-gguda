package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.MemberResponseDto;
import com.springboot.gguda.data.dto.ReviewResponseDto;
import com.springboot.gguda.data.dto.apply.ApplymentPartnersResponseDto;
import com.springboot.gguda.data.dto.apply.EstimateElecResponseDto;
import com.springboot.gguda.data.dto.apply.EstimateEventResponseDto;
import com.springboot.gguda.data.dto.apply.EstimateTVResponseDto;
import com.springboot.gguda.data.entity.Member;
import com.springboot.gguda.data.repository.ApplymentPartnersRepository;
import com.springboot.gguda.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final ApplymentPartnersService applymentPartnersService;
    private final MemberService memberService;
    private final EstimateElecService estimateElecService;
    private final EstimateTVService estimateTVService;
    private final EstimateEventService estimateEventService;
    private final ApplymentPartnersRepository applymentPartnersRepository;

    @Autowired
    public AdminController(ApplymentPartnersService applymentPartnersService,
                           MemberService memberService,
                           ApplymentPartnersRepository applymentPartnersRepository,
                           EstimateTVService estimateTVService,
                           EstimateEventService estimateEventService,
                           EstimateElecService estimateElecService) {
        this.applymentPartnersService = applymentPartnersService;
        this.memberService = memberService;
        this.estimateElecService = estimateElecService;
        this.estimateTVService = estimateTVService;
        this.estimateEventService = estimateEventService;
        this.applymentPartnersRepository = applymentPartnersRepository;
    }


    // 견적신청List확인(전자기기)

    @GetMapping(value = "/estimate-elec")           //  신청목록 쭉 보기
    public List<EstimateElecResponseDto> getAllEstimateElec() {
        List<EstimateElecResponseDto> all = estimateElecService.getAllEstimateElec();

        return all;
    }

    // 견적신청List확인(전자기기-TV)

    @GetMapping(value = "/estimate-tv")           //  신청목록 쭉 보기
    public List<EstimateTVResponseDto> getAllEstimateTV() {
        List<EstimateTVResponseDto> all = estimateTVService.getAllEstimateTV();

        return all;
    }

    // 견적신청디테일보기 (전자기기)

    @GetMapping(value = "/estimate-elec/detail")
    public EstimateElecResponseDto getEstimateElec(Long id) {
        EstimateElecResponseDto estimateElecResponseDto = estimateElecService.getEstimateElec(id);

        return estimateElecResponseDto;
    }

    // 견적신청디테일보기 (TV)

    @GetMapping(value = "/estimate-tv/detail")
    public EstimateTVResponseDto getEstimateTV(Long id) {
        EstimateTVResponseDto estimateTVResponseDto = estimateTVService.getEstimateTV(id);

        return estimateTVResponseDto;
    }




    // 견적신청List확인(행사용품)

    @GetMapping(value = "/estimate-event")           //  신청목록 쭉 보기
    public List<EstimateEventResponseDto> getAllEstimateEvent() {
        List<EstimateEventResponseDto> all = estimateEventService.getAllEstimateEvent();

        return all;
    }

    // 견적신청디테일보기 (행사용품)

    @GetMapping(value = "/estimate-event/detail")
    public EstimateEventResponseDto getEstimateEvent(Long id) {
        EstimateEventResponseDto estimateEventResponseDto = estimateEventService.getEstimateEvent(id);

        return estimateEventResponseDto;
    }

    // 견적신청허가(행사용품)

    // 파트너스신청확인

    @GetMapping(value = "/applyment-partners")           // 파트너스 신청목록 쭉 보기
    public List<ApplymentPartnersResponseDto> getAllApplymentPartners() {
        List<ApplymentPartnersResponseDto> all = applymentPartnersService.getAllApplymentPartners();

        return all;
    }

    // 파트너스신청허가

    @PutMapping(value = "/applyment-partners/permission")
    public MemberResponseDto putMemberPartnersAuthority(Long id) {
//        Member member = memberService. // id에 해당하는 파트너스신청목록조회 ->memberId에 해당하는 member의 권한바꾸기

        Member member = applymentPartnersRepository.findById(id).get().getMember();
        member.setParterAutho(1);

        Long memberId = member.getId();
        MemberResponseDto memberResponseDto = memberService.getMember(memberId);

        return memberResponseDto;
    }

    // 회원목록 조회
    @GetMapping(value = "/member-view")
    public List<MemberResponseDto> getMemberList() {
        List<MemberResponseDto> members = memberService.getMemberList();

        return members;
    }


    // 후기쓰기, 상품구매시 적립금 지급!




}
