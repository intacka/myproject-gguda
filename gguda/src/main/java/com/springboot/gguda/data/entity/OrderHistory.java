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
@Table(name = "order_history")
public class OrderHistory extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String state;       //상태 : 결제완료, 배송중, 배송완료

    @ManyToOne
    @JoinColumn(name = "product_id")            // Product 엔티티와 다대일설정. 외래키 이름 = "product_id"
    private Product product;

    @ManyToOne
    @JoinColumn(name = "member_id")            // Product 엔티티와 다대일설정. 외래키 이름 = "product_id"
    private Member member;




}
