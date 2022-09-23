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

    @Column(unique = true, nullable = false)
    private String member_id; // 아이디

    private String member_pw; // 비밀번호

    private String email; // 이메일

    private boolean parter_autho; // 파트너스권한

    private boolean cospo_autho; // 코스포권한

    private Integer phone_num; // 전화번호

    private String address; // 주소

    private Integer gender; // 성별

    private Integer date_of_birth; // 생년월일

    private boolean marketing_consent; // 마케팅정보수신동의여부

    private Integer reserves; // 적립금

    private boolean buisness_reg; // 사업자등록여부

}
