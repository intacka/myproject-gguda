package com.springboot.gguda.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "product")
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 상품고유번호

    @Column(name = "name", unique = true)
    private String name; // 상품명

    @Column(name = "price")
    private Integer price; // 상품가격

    @Column(name = "sales_amount")
    private Integer salesAmount; // 판매량

    @Column(name = "stock")
    private Integer stock; // 재고량

    @Column(name = "sales_state")
    private Integer salesState; // 판매타입 (0:판매중 , 1:품절)

    @Column(name = "brand")
    private String brand; // 브랜드

    // 상세사진 첨부 할수있어야함
}
