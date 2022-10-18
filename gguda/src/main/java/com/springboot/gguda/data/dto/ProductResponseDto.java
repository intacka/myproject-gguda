package com.springboot.gguda.data.dto;

import com.springboot.gguda.data.entity.ImageFile;
import lombok.*;
import org.springframework.core.io.UrlResource;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class ProductResponseDto {

    private Long id; // 상품고유번호
    private String name; // 상품명
    private Integer price; // 상품가격
    private LocalDateTime createdAt; // 등록날짜
    private LocalDateTime updatedAt; // 수정날짜
    private Integer sales; // 판매량
    private String brand; // 브랜드
    private Integer stock; // 재고량
    private Integer salesType; // 판매상태(판매중,품절)
    private List<ImageFile> imageFiles;
}
