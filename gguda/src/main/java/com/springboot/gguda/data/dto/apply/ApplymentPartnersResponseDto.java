package com.springboot.gguda.data.dto.apply;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class ApplymentPartnersResponseDto {

    private Long id;
    private String businessName;
    private String businessNum;
    private String representative;
    private String businessAddress;

    private String serviceName;
    private String siteAddress;
    private String storeLocation;
    private String category;
    private String dealMethod;

    private String name;
    private String phoneNum;
    private String ggudaId;
    private String adminId;
    private String etcContent;
    private String email;
    private String taxEmail;

    private Long memberId;
    private LocalDateTime createdAt; // 등록날짜
    private LocalDateTime updatedAt; // 수정날짜
}
