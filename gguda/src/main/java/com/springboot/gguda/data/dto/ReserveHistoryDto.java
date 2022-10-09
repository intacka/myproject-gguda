package com.springboot.gguda.data.dto;

import com.springboot.gguda.data.entity.Member;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ReserveHistoryDto {

    private String reason;  // 사유
    private Integer historyPrice;    // 변동금액
    private Integer type; // 유형 (지급:0, 사용:1)
    private Long memberId;

}
