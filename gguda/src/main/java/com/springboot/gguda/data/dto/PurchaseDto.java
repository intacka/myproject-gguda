package com.springboot.gguda.data.dto;

import com.springboot.gguda.data.entity.PurchaseProductInfo;
import lombok.*;

import javax.persistence.Column;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PurchaseDto {

    private String howPay;          // 결제방법
    private Long couponId;          // 사용된 쿠폰고유 id
    private Long useReserve;        // 사용된 적립금
    private Long totalPrice;        // 최종결제금액
    private String state;       // 상태(결제완료,배송중,배송완료)

    private List<PurchaseProductInfoDto> purchaseProductInfoDtos;      // 상품목록

//    private Long shippingInfoId;      // 배송정보(엔티티)
    private String name;        // 주문자이름
    private String address;     // 주문자주소
    private String phoneNum;   // 주문자 핸드폰
    private String req;         // 주문자 요청사항

    private Long memberId;

}
