package com.springboot.gguda.data.dto;

import com.springboot.gguda.data.entity.ImageFile;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class EventProductResponseDto {

    private Long id;
    private String name; // 상품명
    private Integer salesType; // 판매상태(판매중,품절)
    private String sort; // 종류
    private LocalDateTime createdAt; // 등록날짜
    private LocalDateTime updatedAt; // 수정날짜
    private List<ImageFile> imageFiles;
}
