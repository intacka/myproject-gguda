package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.*;
import com.springboot.gguda.result.LoginResult;

import java.util.List;

public interface MemberService {
    MemberResponseDto saveMemberDto(MemberDto memberDto);

    MemberResponseDto getMember(Long id);

    List<MemberResponseDto> getMemberList();

    LoginResult login(String memberId, String memberPw);

    boolean doubleCheck(String memberId);
}
