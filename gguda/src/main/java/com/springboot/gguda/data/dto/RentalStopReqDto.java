package com.springboot.gguda.data.dto;

import com.springboot.gguda.data.entity.Member;
import com.springboot.gguda.data.entity.Product;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RentalStopReqDto {
    private String stopReqDate;            // 중지요청일
    private String etcReq;              // 기타요청사항
    private Long memberId;
    private Long productId;

}
