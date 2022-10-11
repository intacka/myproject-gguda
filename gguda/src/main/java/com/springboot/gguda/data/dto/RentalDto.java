package com.springboot.gguda.data.dto;

import com.springboot.gguda.data.entity.Member;
import com.springboot.gguda.data.entity.Product;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RentalDto {
    private String name;
    private String shippingAddress;          // 사용된 쿠폰고유 id
    private Integer rentalMonth;            // 렌탈개월수
    private Integer monthPrice;             // 다달이 결제금액
    private String cardNum;            // 카드번호
    private String exPeriod;        // 유효기간
    private String dateOfBirth;     //생년월일
    private Integer payDate;    // 납부일
    private String thisDatePayedState; // 이달납부상태
    private Long memberId;
    private Long productId;

}
