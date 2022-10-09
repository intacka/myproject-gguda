package com.springboot.gguda.data.dto.apply;

import com.springboot.gguda.data.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class EstimateTVResponseDto extends BaseEntity {

    private Long id;
    private Integer volumn;             //수량
    private String tvSize;               //화면크기
    private String installType;        //설치방식
    private String connectSort;         //연결종류
    private String link;         //링크

    private String eventContent;    // 행사내용
    private String purpose;         // 용도
    private String installDate;      // 설치날짜
    private String collectDate;      // 회수날짜
    private String address;         // 주소
    private Integer installYn;      // 설치여부
    private Integer elevatorYn;     // 엘리베이터 여부
    private String managerName;     // 담당자 성함
    private String managerContact;  // 담당자연락처
    private String managerEmail;    // 담당자이메일
    private String taxEmail;        // 세금계산서이메일
    private Integer isConfirmed;        // 승인여부

    private Long memberId;
    private LocalDateTime createdAt; // 등록날짜
    private LocalDateTime updatedAt; // 수정날짜
}
