package com.springboot.gguda.data.dto;

import com.springboot.gguda.data.entity.Purchase;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ShippingInfoDto {

    private String name;
    private String address;
    private String phoneNum; // 전화번호
    private String req; // 요청사항
    private Long purchaseId;

}
