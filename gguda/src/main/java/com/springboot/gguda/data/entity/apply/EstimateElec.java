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
@Table(name = "estimate_elec")
public class EstimateElec extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "volumn")
    private Integer volumn;             //수량
    @Column(name = "monitor_size")
    private String monitorSize;         //모니터사이즈
    @Column(name = "cpu")
    private String cpu;                 //cpu
    @Column(name = "ram")
    private String ram;                 //ram
    @Column(name = "ssd")
    private String ssd;                 //ssd
    @Column(name = "os")
    private String os;                  //os
    @Column(name = "others")
    private String others;               //주변기기
    @Column(name = "internet_system")
    private String internetSystem;      //인터넷환경
    @Column(name = "add_program")
    private String addProgram;          //추가프로그램
    @Column(columnDefinition = "TEXT", name = "link")
    private String link;                //링크

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

    @Column(name = "is_confirmed")
    private Integer isConfirmed;        // 승인여부
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
