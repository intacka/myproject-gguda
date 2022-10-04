package com.springboot.gguda.result;

import com.springboot.gguda.data.dto.*;
import lombok.Data;

import java.util.List;

@Data // Getter, Setter, ToString, EqualsAndHashCode
public class MemberResult<T> {
    private boolean result;
    private MemberResponseDto memberResponseDto;

    public MemberResult(MemberResponseDto memberResponseDto, boolean result) {
        this.memberResponseDto = memberResponseDto;
        this.result = result;
    }
}