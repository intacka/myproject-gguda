package com.springboot.gguda.data.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class RentalResponseDto {

    private Long id;

    private String name;
    private String shippingAddress;          // 사용된 쿠폰고유 id
    private Integer rentalMonth;            // 렌탈개월수
    private Integer monthPrice;             // 다달이 결제금액
    private String cardNum;            // 카드번호
    private String exPeriod;        // 유효기간
    private String dateOfBirth;     //생년월일
    private Integer payDate;    // 납부일
    private Long memberId;
    private Long productId;
    private LocalDateTime endDate; // 끝나는 날
    private String thisDatePayedState; // 이달납부상태

    private LocalDateTime createdAt; // 등록날짜
    private LocalDateTime updatedAt; // 수정날짜

}
