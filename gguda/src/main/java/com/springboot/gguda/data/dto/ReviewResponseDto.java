package com.springboot.gguda.data.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class ReviewResponseDto {

    private Long id;
    private String content;                     // 내용
    private Integer stars;            // 평점
    private LocalDateTime createdAt; // 등록날짜
    private LocalDateTime updatedAt; // 수정날짜
    private Long productId;
    private Long memberId;

}
