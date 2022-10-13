package com.springboot.gguda.result;

import com.springboot.gguda.data.dto.PurchaseResponseDto;
import com.springboot.gguda.data.dto.ShippingInfoResponseDto;
import lombok.Data;

@Data // Getter, Setter, ToString, EqualsAndHashCode
public class LoginResult<T> {
    private boolean result;
    private Long id;

    public LoginResult(boolean result,
                       Long id) {
        this.result = result;
        this.id = id;
    }
}