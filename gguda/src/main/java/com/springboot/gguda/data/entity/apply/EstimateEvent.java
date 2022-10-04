package com.springboot.gguda.data.entity.apply;

import com.springboot.gguda.data.entity.BaseEntity;
import com.springboot.gguda.data.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "estimate_event")
public class EstimateEvent extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "volumn")
    private Integer volumn;             //수량
    @Column(columnDefinition = "TEXT", name = "link")
    private String link;         //링크

    @Column(name = "event_content")
    private String eventContent;    // 행사내용
    @Column(name = "purpose")
    private String purpose;         // 용도
    @Column(name = "install_date")
    private String installDate;      // 설치날짜
    @Column(name = "collect_date")
    private String collectDate;      // 회수날짜
    @Column(columnDefinition = "TEXT", name = "address")
    private String address;         // 주소
    @Column(name = "install_yn")
    private Integer installYn;      // 설치여부
    @Column(name = "elevator_yn")
    private Integer elevatorYn;     // 엘리베이터 여부
    @Column(name = "manager_name")
    private String managerName;     // 담당자 성함
    @Column(name = "manager_contact")
    private String managerContact;  // 담당자연락처
    @Column(name = "manager_email")
    private String managerEmail;    // 담당자이메일
    @Column(name = "tax_email")
    private String taxEmail;        // 세금계산서이메일

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
