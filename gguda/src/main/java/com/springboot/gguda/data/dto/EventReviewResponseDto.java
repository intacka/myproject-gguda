package com.springboot.gguda.data.dto;

import com.springboot.gguda.data.entity.ImageFile;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class EventReviewResponseDto {

    private Long id;
    private String content;                     // 내용
    private Integer stars;            // 평점
    private LocalDateTime createdAt; // 등록날짜
    private LocalDateTime updatedAt; // 수정날짜
    private Long eventProductId;
    private Long memberId;
    private List<ImageFile> imageFiles;

}
