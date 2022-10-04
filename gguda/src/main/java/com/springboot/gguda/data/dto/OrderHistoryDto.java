package com.springboot.gguda.data.dto;

import com.springboot.gguda.data.entity.Member;
import com.springboot.gguda.data.entity.Product;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderHistoryDto {

    private String state;       //상태 : 결제완료, 배송중, 배송완료
    private Long productId;
    private Long memberId;

}
