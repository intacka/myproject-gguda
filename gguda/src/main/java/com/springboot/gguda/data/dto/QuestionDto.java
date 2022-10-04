package com.springboot.gguda.data.dto;

import com.springboot.gguda.data.entity.Product;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class QuestionDto {

    private String title;                           // 제목
    private String content;                     // 내용
    private Integer privateWhether;            // 비밀여부 - 0:No, 1:Yes
    private Long productId;
    private Long memberId;

}
