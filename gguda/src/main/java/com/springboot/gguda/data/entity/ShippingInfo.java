package com.springboot.gguda.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "purchase")
public class ShippingInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // 고유 id
    private String name;
    @Column(columnDefinition = "TEXT", name = "address")
    private String address;
    @Column(name = "phone_num")
    private String phoneNum; // 전화번호
    @Column(name = "req")
    private String req; // 요청사항

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

}
