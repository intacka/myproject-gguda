package com.springboot.gguda.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "basket")
public class Basket extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "median_price")
    private Long medianPrice;             // 중간금액

    @ManyToOne
    @JoinColumn(name = "product_id", unique = true)            // Product 엔티티와 다대일설정. 외래키 이름 = "product_id"
    private Product product;

    @OneToOne
    @JoinColumn(name = "member_id")            // Product 엔티티와 다대일설정. 외래키 이름 = "product_id"
    private Member member;
}
