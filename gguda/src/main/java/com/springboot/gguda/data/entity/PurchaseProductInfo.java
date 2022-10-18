package com.springboot.gguda.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Table(name = "purchase_product_info")
public class PurchaseProductInfo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // 고유 id
    @Column(name = "product_id")
    private Integer productId;      // 상품고유아이디
    private Integer amount;         // 수량



}
