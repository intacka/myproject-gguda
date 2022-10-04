package com.springboot.gguda.data.dto;

import lombok.*;

import javax.persistence.Column;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EventProductDto {

    private String name; // 상품명
    private Integer salesType; // 판매상태(판매중,품절)
    private String sort; // 종류


}
