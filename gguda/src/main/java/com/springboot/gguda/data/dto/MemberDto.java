package com.springboot.gguda.data.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MemberDto {

    private String memberId; // 아이디
    private String memberPw; // 비밀번호
    private String email; // 이메일
    private Integer parterAutho; // 파트너스권한
    private String phoneNum; // 전화번호
    private String address; // 주소
    private String gender; // 성별
    private Integer dateOfBirth; // 생년월일
    private Integer marketingConsent; // 마케팅정보수신동의여부
    private Integer reserves; // 적립금
    private String name;
//    private Integer buisnessReg; // 사업자등록여부

}
