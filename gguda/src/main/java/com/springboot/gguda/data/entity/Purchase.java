package com.springboot.gguda.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "purchase")
public class Purchase extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // 고유 id
    @Column(name = "how_pay")
    private String howPay;          // 결제방법
    @Column(name = "coupon_id")
    private Long couponId;          // 사용된 쿠폰고유 id
    @Column(name = "use_reserve")
    private Long useReserve;        // 사용된 적립금
    @Column(name = "total_price")
    private Long totalPrice;        // 최종결제금액
    private String state;       // 상태(결제완료,배송중,배송완료)

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "purchase_product_infos")
    private List<PurchaseProductInfo> purchaseProductInfos = new ArrayList<>();      // 상품목록

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
