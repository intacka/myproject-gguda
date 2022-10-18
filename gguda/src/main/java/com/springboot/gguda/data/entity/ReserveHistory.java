package com.springboot.gguda.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "reserve_history")
public class ReserveHistory extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "reason")
    private String reason;  // 사유

    @Column(name = "history_price")
    private Integer historyPrice;    // 변동금액

    @Column(name = "type")
    private Integer type; // 유형 (지급:0, 사용:1)

    @ManyToOne
    @JoinColumn(name = "member_id")            // Member 엔티티와 다대일설정. 외래키 이름 = "member_id"
    private Member member;

}
