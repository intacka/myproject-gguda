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
public class PurchaseProductInfoResponseDto {

    private Integer productId;      // 상품고유아이디
    private Integer amount;         // 수량

}
