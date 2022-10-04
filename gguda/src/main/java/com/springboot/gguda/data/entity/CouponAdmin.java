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
    private LocalDate exPeriod; // 유효기간

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // 쿠폰일련번호, 유효기간 , member매핑은 CouponUser에게 맡기자


}
