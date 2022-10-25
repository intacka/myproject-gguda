package com.springboot.gguda.data.dto.apply;

import lombok.*;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ApplymentPartnersDto {

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
}
