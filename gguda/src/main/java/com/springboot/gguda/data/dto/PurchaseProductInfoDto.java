package com.springboot.gguda.data.dto;

import com.springboot.gguda.data.entity.PurchaseProductInfo;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PurchaseProductInfoDto {

    private Integer productId;      // 상품고유아이디
    private Integer amount;         // 수량

}
