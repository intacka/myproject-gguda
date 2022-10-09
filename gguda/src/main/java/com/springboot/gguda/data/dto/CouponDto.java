package com.springboot.gguda.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CouponDto {

    private Long num;       // 쿠폰 일련번호
    private String name;    // 쿠폰 이름
    private Double rate;    // 할인율
    private String exPeriod; // 유효기간
    private Integer use;     // 사용여부
    private String memberId;

}
