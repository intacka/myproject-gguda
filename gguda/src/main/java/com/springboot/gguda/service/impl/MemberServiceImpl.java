package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.MemberDto;
import com.springboot.gguda.data.dto.MemberResponseDto;
import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.data.entity.Member;
import com.springboot.gguda.data.entity.Product;
import com.springboot.gguda.data.repository.MemberRepository;
import com.springboot.gguda.data.repository.ProductRepository;
import com.springboot.gguda.data.repository.QuestionRepository;
import com.springboot.gguda.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberResponseDto saveMemberDto(MemberDto memberDto) {
        Member member = new Member();
        member.setMemberId(memberDto.getMemberId());
        member.setMemberPw(memberDto.getMemberPw());
        member.setEmail(memberDto.getEmail());
        member.setParterAutho(memberDto.getParterAutho());
        member.setCospoAutho(memberDto.getCospoAutho());
        member.setPhoneNum(memberDto.getPhoneNum());
        member.setAddress(memberDto.getAddress());
        member.setGender(memberDto.getGender());
        member.setDateOfBirth(memberDto.getDateOfBirth());
        member.setMarketingConsent(memberDto.getMarketingConsent());
        member.setReserves(memberDto.getReserves());
        member.setBuisnessReg(memberDto.getBuisnessReg());

        memberRepository.save(member);

        MemberResponseDto memberResponseDto = new MemberResponseDto();
        memberResponseDto.setMemberId(member.getMemberId());
        memberResponseDto.setMemberPw(member.getMemberPw());
        memberResponseDto.setEmail(member.getEmail());
        memberResponseDto.setParterAutho(member.getParterAutho());
        memberResponseDto.setCospoAutho(member.getCospoAutho());
        memberResponseDto.setPhoneNum(member.getPhoneNum());
        memberResponseDto.setAddress(member.getAddress());
        memberResponseDto.setGender(member.getGender());
        memberResponseDto.setDateOfBirth(member.getDateOfBirth());
        memberResponseDto.setMarketingConsent(member.getMarketingConsent());
        memberResponseDto.setReserves(member.getReserves());
        memberResponseDto.setBuisnessReg(member.getBuisnessReg());
        memberResponseDto.setId(member.getId());

        return memberResponseDto;
    }
}
