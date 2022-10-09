package com.springboot.gguda.data.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class CouponResponseDto {

    private Long id;
    private Long num;       // 쿠폰 일련번호
    private String name;    // 쿠폰 이름
    private Double rate;    // 할인율
    private String exPeriod; // 유효기간
    private Integer use;     // 사용여부
    private String memberId;
    private LocalDateTime createdAt; // 등록날짜
    private LocalDateTime updatedAt; // 수정날짜

}
