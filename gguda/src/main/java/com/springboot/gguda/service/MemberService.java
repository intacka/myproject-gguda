package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.*;

public interface MemberService {
    MemberResponseDto saveMemberDto(MemberDto memberDto);

    MemberResponseDto getMember(Long id);
}
