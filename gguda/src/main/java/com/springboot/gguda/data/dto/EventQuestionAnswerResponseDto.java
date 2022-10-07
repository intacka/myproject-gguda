package com.springboot.gguda.data.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class EventQuestionAnswerResponseDto {

    private Long id;                    // 고유 id
    private String content;                     // 내용
    private Integer privateWhether;            // 비밀여부 - 0:No, 1:Yes
    private LocalDateTime createdAt; // 등록날짜
    private LocalDateTime updatedAt; // 수정날짜
    private Long eventQuestionId;

}
