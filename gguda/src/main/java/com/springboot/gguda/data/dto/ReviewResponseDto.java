package com.springboot.gguda.data.dto;

import lombok.*;

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
    private Date regDate;
    private Long productId;
    private Long memberId;

}
