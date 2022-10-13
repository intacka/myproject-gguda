package com.springboot.gguda.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "member")
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고유번호

    @Column(unique = true, nullable = false, name = "member_id")
    private String memberId; // 아이디

    @Column(name = "member_pw")
    private String memberPw; // 비밀번호

    @Column(name = "email")
    private String email; // 이메일

    @Column(name = "parter_autho")
    private Integer parterAutho; // 파트너스권한     NO:0 , YES:1

    @Column(name = "phone_num")
    private String phoneNum; // 전화번호

    @Column(columnDefinition = "TEXT", name = "address")
    private String address; // 주소

    @Column(name = "gender")
    private String gender; // 성별

    @Column(name = "date_of_birth")
    private Integer dateOfBirth; // 생년월일

    @Column(name = "marketing_consent")
    private Integer marketingConsent; // 마케팅정보수신동의여부

    @Column(name = "reserves")
    private Integer reserves; // 적립금

    @Column(name = "name")
    private String name;    // 이름

//    @Column(name = "buisness_reg")
//    private Integer buisnessReg; // 사업자등록여부

}
