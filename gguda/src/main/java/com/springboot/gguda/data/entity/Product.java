package com.springboot.gguda.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 상품고유번호

    @Column(name = "name", unique = true)
    private String name; // 상품명

    @Column(name = "price")
    private Integer price; // 상품가격

    @Column(name = "product_type")
    private Integer productType; // 상품타입 : 렌탈-0,판매-1

    @Column(name = "reg_date")
    private Date regDate; // 등록날짜

    @Column(name = "sales")
    private Integer sales; // 판매량

    @Column(name = "stock")
    private Integer stock; // 재고량

    @Column(name = "sales_type")
    private Integer salesType; // 판매상태(판매중,품절)

    @Column(name = "brand")
    private String brand; // 브랜드

    // 상세사진 첨부 할수있어야함
}