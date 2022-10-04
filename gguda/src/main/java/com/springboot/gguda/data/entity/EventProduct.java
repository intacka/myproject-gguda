package com.springboot.gguda.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "event_product")
public class EventProduct extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name; // 상품명

    @Column(name = "sales_type")
    private Integer salesType; // 판매상태(판매중,품절)

    @Column(name = "sort")
    private String sort; // 종류
}
