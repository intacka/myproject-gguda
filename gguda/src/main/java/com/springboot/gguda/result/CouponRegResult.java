package com.springboot.gguda.result;

import com.springboot.gguda.data.dto.CouponResponseDto;
import lombok.Data;

@Data // Getter, Setter, ToString, EqualsAndHashCode
public class CouponRegResult<T> {
    private int N;
    private String result;
    private CouponResponseDto couponResponseDto;

    public CouponRegResult(int N, String result, CouponResponseDto couponResponseDto) {
        this.N = N;
        this.result = result;
        this.couponResponseDto = couponResponseDto;
    }
}