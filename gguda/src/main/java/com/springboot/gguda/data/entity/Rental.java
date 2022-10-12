package com.springboot.gguda.data.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "rental")
public class Rental extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // 고유 id
    private String name;
    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "rental_month")
    private Integer rentalMonth;            // 렌탈개월수

    @Column(name = "month_price")
    private Integer monthPrice;             //   다달이 결제금액

    @Column(name = "card_num")
    private String cardNum;            // 카드번호

    @Column(name = "ex_period")
    private String exPeriod;        // 유효기간

    @Column(name = "date_of_birth")
    private String dateOfBirth;     //생년월일

    @Column(name = "pay_date")
    private Integer payDate;    // 납부일

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "end_date")
    private LocalDateTime endDate; // 끝나는 날

    @Column(name = "this_date_payed_state")
    private String thisDatePayedState; // 이달납부상태


}
