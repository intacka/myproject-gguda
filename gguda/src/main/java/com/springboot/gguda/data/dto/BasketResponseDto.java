package com.springboot.gguda.data.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class BasketResponseDto {

    private Long id;                    // 고유 id
    private Long amount;
    private Long medianPrice;
    private LocalDateTime createdAt; // 등록날짜
    private LocalDateTime updatedAt; // 수정날짜
    private Long productId;
    private Long memberId;

}
