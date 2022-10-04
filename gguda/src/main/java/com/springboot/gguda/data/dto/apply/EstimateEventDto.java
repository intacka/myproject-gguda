package com.springboot.gguda.data.dto.apply;

import com.springboot.gguda.data.entity.BaseEntity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EstimateEventDto extends BaseEntity {

    private Integer volumn;             //수량
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

    private Long memberId;

}
