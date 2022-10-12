package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.MemberResponseDto;
import com.springboot.gguda.data.dto.apply.ApplymentPartnersDto;
import com.springboot.gguda.data.dto.apply.ApplymentPartnersResponseDto;
import com.springboot.gguda.data.entity.Member;
import com.springboot.gguda.data.repository.ApplymentPartnersRepository;
import com.springboot.gguda.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partners-apply")
public class PartnersController {
    private final ApplymentPartnersService applymentPartnersService;
    private final MemberService memberService;
    private final ApplymentPartnersRepository applymentPartnersRepository;

    @Autowired
    public PartnersController(ApplymentPartnersService applymentPartnersService,
                              MemberService memberService,
                              ApplymentPartnersRepository applymentPartnersRepository) {
        this.applymentPartnersService = applymentPartnersService;
        this.memberService = memberService;
        this.applymentPartnersRepository = applymentPartnersRepository;
    }


    // 파트너스권한신청 API
    @PostMapping(value = "/apply")
    public ResponseEntity<ApplymentPartnersResponseDto> applyPartners(@RequestBody ApplymentPartnersDto applymentPartnersDto) {
        ApplymentPartnersResponseDto applymentPartnersResponseDto =
                applymentPartnersService.saveApplymentPartnersDto(applymentPartnersDto);

        return ResponseEntity.status(HttpStatus.OK).body(applymentPartnersResponseDto);
    }

    // 파트너스신청확인
    @GetMapping(value = "/view-list")           // 파트너스 신청목록 쭉 보기
    public List<ApplymentPartnersResponseDto> getAllApplymentPartners() {
        List<ApplymentPartnersResponseDto> all = applymentPartnersService.getAllApplymentPartners();

        return all;
    }

    // 파트너스신청허가
    @PutMapping(value = "/permission")
    public MemberResponseDto putMemberPartnersAuthority(Long id) {
        Member member = applymentPartnersRepository.findById(id).get().getMember();
        member.setParterAutho(1);

        Long memberId = member.getId();
        MemberResponseDto memberResponseDto = memberService.getMember(memberId);

        return memberResponseDto;
    }


}
