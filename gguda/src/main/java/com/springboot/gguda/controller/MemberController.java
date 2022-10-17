package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.MemberDto;
import com.springboot.gguda.data.dto.MemberResponseDto;
import com.springboot.gguda.data.entity.Member;
import com.springboot.gguda.data.repository.MemberRepository;
import com.springboot.gguda.result.LoginResult;
import com.springboot.gguda.result.MemberResult;
import com.springboot.gguda.service.MemberService;
import com.springboot.gguda.service.ReserveHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final ReserveHistoryService reserveHistoryService;

    @Autowired
    public MemberController(MemberService memberService,
                            MemberRepository memberRepository,
                            ReserveHistoryService reserveHistoryService) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.reserveHistoryService = reserveHistoryService;
    }


    // 회원정보 return API
    @GetMapping(value = "/view-info")
    public MemberResponseDto getMember(Long id) {
        MemberResponseDto myInfo = memberService.getMember(id);

        return myInfo;
    }

    // 회원탈퇴 API
    @PostMapping(value = "/delete")
    public MemberResult dropMember(@RequestParam Long id, String memberPw) {

        Optional<Member> optionalMember = memberRepository.findById(id);
        Member member = optionalMember.get();

        MemberResponseDto memberResponseDto = new MemberResponseDto();

        if(memberPw.equals(member.getMemberPw())) {
            member.setMemberPw(null);
            member.setEmail(null);
            member.setParterAutho(null);
            member.setPhoneNum(null);
            member.setAddress(null);
            member.setGender(null);
            member.setDateOfBirth(null);
            member.setMarketingConsent(null);
            member.setReserves(null);

            memberResponseDto.setId(member.getId());
            memberResponseDto.setMemberId(member.getMemberId());
            memberResponseDto.setCreatedAt(member.getCreatedAt());
            memberResponseDto.setUpdatedAt(member.getUpdatedAt());

            return new MemberResult(memberResponseDto, true);
        } else {
            return new MemberResult(null, false);
        }

    }

    // 회원목록 조회
    @GetMapping(value = "/view-list")
    public List<MemberResponseDto> getMemberList() {
        List<MemberResponseDto> members = memberService.getMemberList();

        return members;
    }


    // 회원 만들기 (회원가입) + 적립금 1000원
    @PostMapping(value = "/register")
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody MemberDto memberDto) {
        MemberResponseDto memberResponseDto = memberService.saveMemberDto(memberDto);
        reserveHistoryService.createSigninReserveHistory(memberResponseDto.getId()); // member적립금 1000원추가, 내역도 추가

        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDto);
    }

    // Login API
    @PostMapping(value = "/login")
    public LoginResult login(String memberId, String memberPw) {
        LoginResult result = memberService.login(memberId, memberPw);
        return result;
    }

    // 회원가입할때 아이디 중복확인
    @PostMapping(value = "/double-check")
    public boolean doubleCheck(String memberId) {
        boolean result = memberService.doubleCheck(memberId);
        return result;
    }






}
