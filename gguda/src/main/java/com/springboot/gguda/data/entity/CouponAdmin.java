package com.springboot.gguda.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "coupon")
public class CouponAdmin extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num")
    private Long num;       // 쿠폰 일련번호

    @Column(name = "name")
    private String name;    // 쿠폰 이름

    @Column(name = "rate")
    private Double rate;    // 할인율

    @Column(name = "ex_period")
    private String exPeriod; // 유효기간

    @Column(name = "member_id")
    private String memberId;    // 회원id



}
