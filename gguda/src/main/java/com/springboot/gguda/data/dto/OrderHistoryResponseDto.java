package com.springboot.gguda.data.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class OrderHistoryResponseDto {

    private Long id;
    private String state;       //상태 : 결제완료, 배송중, 배송완료
    private Long productId;
    private Long memberId;
    private LocalDateTime createdAt; // 등록날짜
    private LocalDateTime updatedAt; // 수정날짜

}
