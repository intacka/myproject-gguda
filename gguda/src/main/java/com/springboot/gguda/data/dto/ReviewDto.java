package com.springboot.gguda.data.dto;

import lombok.*;

import javax.persistence.Column;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ReviewDto {

    private String content;                     // 내용
    private Integer stars;            // 평점
    private Long productId;
    private Long memberId;

}
