package com.springboot.gguda.data.dto;

import com.springboot.gguda.data.entity.PurchaseProductInfo;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class PurchaseResponseDto {

    private Long id;

    private String howPay;          // 결제방법
    private Long couponId;          // 사용된 쿠폰고유 id
    private Long useReserve;        // 사용된 적립금
    private Long totalPrice;        // 최종결제금액
    private String state;       // 상태(결제완료,배송중,배송완료)

    private List<PurchaseProductInfoResponseDto> purchaseProductInfoResponseDtos;
    // 상품과수량 목록

    private Long memberId;


    private LocalDateTime createdAt; // 등록날짜
    private LocalDateTime updatedAt; // 수정날짜

}
