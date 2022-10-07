package com.springboot.gguda.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BasketDto {

    private Long amount;
    private Long productId;
    private Long memberId;
    private Long medianPrice;

}
