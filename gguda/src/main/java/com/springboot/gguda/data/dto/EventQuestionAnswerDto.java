package com.springboot.gguda.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EventQuestionAnswerDto {

    private String content;                     // 내용
    private Integer privateWhether;            // 비밀여부 - 0:No, 1:Yes
    private Long eventQuestionId;

}
