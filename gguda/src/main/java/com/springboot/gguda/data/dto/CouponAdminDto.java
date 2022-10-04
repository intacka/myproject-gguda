package com.springboot.gguda.data.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CouponAdminDto {

    private Long num;       // 쿠폰 일련번호
    private String name;    // 쿠폰 이름
    private Double rate;    // 할인율
    private LocalDate exPeriod; // 유효기간
    private Long memberId;

}
