package com.springboot.gguda.result;

import com.springboot.gguda.data.dto.MemberResponseDto;
import com.springboot.gguda.data.dto.ProductResponseDto;
import lombok.Data;

@Data // Getter, Setter, ToString, EqualsAndHashCode
public class BasketResult<T> {
    private ProductResponseDto productResponseDto;
    private Long amount;
    private Long medianPrice;

    public BasketResult(ProductResponseDto productResponseDto, Long amount, Long medianPrice) {
        this.productResponseDto = productResponseDto;
        this.amount = amount;
        this.medianPrice = medianPrice;
    }
}