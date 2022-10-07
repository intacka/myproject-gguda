package com.springboot.gguda.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EventBasketDto {

    private Long amount;
    private Long eventProductId;
    private Long memberId;

}
