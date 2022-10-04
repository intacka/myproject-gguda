package com.springboot.gguda.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EventReviewDto {

    private String content;                     // 내용
    private Integer stars;            // 평점
    private Long eventProductId;
    private Long memberId;

}
