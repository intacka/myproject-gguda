package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.MemberDto;
import com.springboot.gguda.data.dto.MemberResponseDto;
import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.data.dto.ReviewResponseDto;
import com.springboot.gguda.data.entity.Member;
import com.springboot.gguda.data.entity.Product;
import com.springboot.gguda.data.entity.Review;
import com.springboot.gguda.data.repository.MemberRepository;
import com.springboot.gguda.data.repository.ProductRepository;
import com.springboot.gguda.data.repository.QuestionRepository;
import com.springboot.gguda.result.LoginResult;
import com.springboot.gguda.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        member.setPhoneNum(memberDto.getPhoneNum());
        member.setAddress(memberDto.getAddress());
        member.setGender(memberDto.getGender());
        member.setDateOfBirth(memberDto.getDateOfBirth());
        member.setMarketingConsent(memberDto.getMarketingConsent());
        member.setReserves(0);
        member.setName(memberDto.getName());

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
        memberResponseDto.setId(member.getId());
        memberResponseDto.setName(member.getName());

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
        memberResponseDto.setName(member.getName());

        return memberResponseDto;
    }

    @Override
    public List<MemberResponseDto> getMemberList() {
        List<Member> members = memberRepository.findAllByOrderByCreatedAtDesc();

        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>();

        for(Member member : members){
            MemberResponseDto dto = MemberResponseDto.builder()
                    .id(member.getId())
                    .memberId(member.getMemberId())
                    .memberPw(member.getMemberPw())
                    .email(member.getEmail())
                    .parterAutho(member.getParterAutho())
                    .phoneNum(member.getPhoneNum())
                    .address(member.getAddress())
                    .gender(member.getGender())
                    .dateOfBirth(member.getDateOfBirth())
                    .marketingConsent(member.getMarketingConsent())
                    .reserves(member.getReserves())
                    .name(member.getName())
                    .createdAt(member.getCreatedAt())
                    .updatedAt(member.getUpdatedAt())
                    .build();

            memberResponseDtoList.add(dto);
        }

        return memberResponseDtoList;
    }

    @Override
    public LoginResult login(String memberId, String memberPw) {

        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);

        if (optionalMember.isPresent()) {
            if (optionalMember.get().getMemberPw().equals(memberPw)) {
                Long id = optionalMember.get().getId();
                return new LoginResult(true, id);
            } else {
                return new LoginResult(false, null);
            }
        } else {
            return new LoginResult(false, null);
        }
    }

    @Override
    public boolean doubleCheck(String memberId) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);

        if (optionalMember.isPresent()) {
            return false;
        } else {
            return true;
        }

    }

}
