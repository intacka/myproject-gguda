package com.springboot.gguda.data.dto;

import com.springboot.gguda.data.entity.Product;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class QuestionResponseDto {

    private Long id;                    // 고유 id
    private String title;                           // 제목
    private String content;                     // 내용
    private Integer privateWhether;            // 비밀여부 - 0:No, 1:Yes
    private LocalDateTime createdAt; // 등록날짜
    private LocalDateTime updatedAt; // 수정날짜
    private Long productId;
    private Long memberId;

}
