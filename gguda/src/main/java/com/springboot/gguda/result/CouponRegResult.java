package com.springboot.gguda.result;

import com.springboot.gguda.data.dto.CouponAdminResponseDto;
import com.springboot.gguda.data.dto.MemberResponseDto;
import lombok.Data;

@Data // Getter, Setter, ToString, EqualsAndHashCode
public class CouponRegResult<T> {
    private int N;
    private String result;
    private CouponAdminResponseDto couponAdminResponseDto;

    public CouponRegResult(int N, String result, CouponAdminResponseDto couponAdminResponseDto) {
        this.N = N;
        this.result = result;
        this.couponAdminResponseDto = couponAdminResponseDto;
    }
}