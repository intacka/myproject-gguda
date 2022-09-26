package com.springboot.gguda.data.entity;

import io.swagger.models.properties.EmailProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "member")
public class Member {

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
    private Integer parterAutho; // 파트너스권한

    @Column(name = "cospo_autho")
    private Integer cospoAutho; // 코스포권한

    @Column(name = "phone_num")
    private Integer phoneNum; // 전화번호

    @Column(name = "address")
    private String address; // 주소

    @Column(name = "gender")
    private Integer gender; // 성별

    @Column(name = "date_of_birth")
    private Integer dateOfBirth; // 생년월일

    @Column(name = "marketing_consent")
    private Integer marketingConsent; // 마케팅정보수신동의여부

    @Column(name = "reserves")
    private Integer reserves; // 적립금

    @Column(name = "buisness_reg")
    private Integer buisnessReg; // 사업자등록여부

}
