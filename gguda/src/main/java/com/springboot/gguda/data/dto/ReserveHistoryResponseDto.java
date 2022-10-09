package com.springboot.gguda.data.dto;

import com.springboot.gguda.data.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class ReserveHistoryResponseDto {

    private Long id;
    private String reason;  // 사유
    private Integer historyPrice;    // 변동금액
    private Integer type; // 유형 (지급:0, 사용:1)
    private Long memberId;
    private LocalDateTime createdAt; // 등록날짜
    private LocalDateTime updatedAt; // 수정날짜

}
