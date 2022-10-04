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
public class CouponAdminResponseDto {

    private Long id;
    private Long num;       // 쿠폰 일련번호
    private String name;    // 쿠폰 이름
    private Double rate;    // 할인율
    private LocalDate exPeriod; // 유효기간
    private Long memberId;          // 평점
    private LocalDateTime createdAt; // 등록날짜
    private LocalDateTime updatedAt; // 수정날짜

}
