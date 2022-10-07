package com.springboot.gguda.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "event_basket")
public class EventBasket extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Long amount;

    @ManyToOne
    @JoinColumn(name = "event_product_id", unique = true)            // Product 엔티티와 다대일설정. 외래키 이름 = "product_id"
    private EventProduct eventProduct;

    @OneToOne
    @JoinColumn(name = "member_id")            // Product 엔티티와 다대일설정. 외래키 이름 = "product_id"
    private Member member;
}
