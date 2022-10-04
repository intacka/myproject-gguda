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
public class CouponUser extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num")
    private Long num;       // 쿠폰 일련번호

    @Column(name = "ex_period")
    private LocalDate exPeriod; // 유효기간

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
