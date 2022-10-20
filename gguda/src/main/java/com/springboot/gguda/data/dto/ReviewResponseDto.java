package com.springboot.gguda.data.dto;

import com.springboot.gguda.data.entity.ImageFile;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class ReviewResponseDto {

    private Long id;
    private String content;                     // 내용
    private Integer stars;            // 평점
    private LocalDateTime createdAt; // 등록날짜
    private LocalDateTime updatedAt; // 수정날짜
    private Long productId;
    private Long memberId;
    private String filename;
    private String filepath;


}
