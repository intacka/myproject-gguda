package com.springboot.gguda.result;

import lombok.Data;

@Data // Getter, Setter, ToString, EqualsAndHashCode
public class PurchaseFromBasketResult<T> {
    private Long productId;
    private Long amount;

    public PurchaseFromBasketResult(Long productId, Long amount) {
        this.productId = productId;
        this.amount = amount;
    }
}