package com.springboot.gguda.result;

import com.springboot.gguda.data.dto.EventProductResponseDto;
import com.springboot.gguda.data.dto.ProductResponseDto;
import lombok.Data;

@Data // Getter, Setter, ToString, EqualsAndHashCode
public class EventBasketResult<T> {
    private EventProductResponseDto eventProductResponseDto;
    private Long amount;

    public EventBasketResult(EventProductResponseDto eventProductResponseDto, Long amount) {
        this.eventProductResponseDto = eventProductResponseDto;
        this.amount = amount;
    }
}