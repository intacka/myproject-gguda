package com.springboot.gguda.data.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class ShippingInfoResponseDto {

    private Long id;
    private String name;
    private String address;
    private String phoneNum; // 전화번호
    private String req; // 요청사항
    private Long purchaseId;
    private LocalDateTime createdAt; // 등록날짜
    private LocalDateTime updatedAt; // 수정날짜

}
