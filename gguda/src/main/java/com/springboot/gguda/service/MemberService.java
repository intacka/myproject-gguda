package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.MemberDto;
import com.springboot.gguda.data.dto.MemberResponseDto;
import com.springboot.gguda.data.dto.QuestionDto;
import com.springboot.gguda.data.dto.QuestionResponseDto;

public interface MemberService {
    MemberResponseDto saveMemberDto(MemberDto memberDto);
}
