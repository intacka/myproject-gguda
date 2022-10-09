package com.springboot.gguda.result;

import com.springboot.gguda.data.dto.CouponResponseDto;
import com.springboot.gguda.data.dto.PurchaseResponseDto;
import com.springboot.gguda.data.dto.ShippingInfoResponseDto;
import lombok.Data;

@Data // Getter, Setter, ToString, EqualsAndHashCode
public class PurchaseResult<T> {
    private PurchaseResponseDto purchaseResponseDto;
    private ShippingInfoResponseDto shippingInfoResponseDto;

    public PurchaseResult(PurchaseResponseDto purchaseResponseDto,
                          ShippingInfoResponseDto shippingInfoResponseDto) {
        this.purchaseResponseDto = purchaseResponseDto;
        this.shippingInfoResponseDto = shippingInfoResponseDto;
    }
}