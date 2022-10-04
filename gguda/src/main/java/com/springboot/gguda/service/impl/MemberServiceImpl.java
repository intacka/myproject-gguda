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

        memberRepository.save(member);

        MemberResponseDto memberResponseDto = new MemberResponseDto();
        memberResponseDto.setMemberId(member.getMemberId());
        memberResponseDto.setMemberPw(member.getMemberPw());
        memberResponseDto.setEmail(member.getEmail());
        memberResponseDto.setParterAutho(member.getParterAutho());
        memberResponseDto.setCreatedAt(member.getCreatedAt());
        memberResponseDto.setUpdatedAt(member.getUpdatedAt());
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

    @Override
    public MemberResponseDto getMember(Long id) {
        Member member = memberRepository.getById(id);

        MemberResponseDto memberResponseDto = new MemberResponseDto();
        memberResponseDto.setId(member.getId());
        memberResponseDto.setMemberId(member.getMemberId());
        memberResponseDto.setMemberPw(member.getMemberPw());
        memberResponseDto.setEmail(member.getEmail());
        memberResponseDto.setParterAutho(member.getParterAutho());
        memberResponseDto.setCreatedAt(member.getCreatedAt());
        memberResponseDto.setUpdatedAt(member.getUpdatedAt());
        memberResponseDto.setPhoneNum(member.getPhoneNum());
        memberResponseDto.setAddress(member.getAddress());
        memberResponseDto.setGender(member.getGender());
        memberResponseDto.setDateOfBirth(member.getDateOfBirth());
        memberResponseDto.setMarketingConsent(member.getMarketingConsent());
        memberResponseDto.setReserves(member.getReserves());
        memberResponseDto.setBuisnessReg(member.getBuisnessReg());

        return memberResponseDto;
    }

}
