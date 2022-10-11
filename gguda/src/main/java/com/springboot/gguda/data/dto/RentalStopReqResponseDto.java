package com.springboot.gguda.data.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class RentalStopReqResponseDto {

    private Long id;

    private String stopReqDate;            // 중지요청일
    private String etcReq;              // 기타요청사항
    private Long memberId;
    private Long productId;

    private LocalDateTime createdAt; // 등록날짜
    private LocalDateTime updatedAt; // 수정날짜

}
