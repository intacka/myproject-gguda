package com.springboot.gguda.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "event_review")
public class EventReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // 고유 id

    @Column(columnDefinition = "TEXT", name = "content")
    private String content;                     // 내용

    @Column(name = "stars")
    private Integer stars;                      // 평점

    @ManyToOne
    @JoinColumn(name = "event_product_id")            // Product 엔티티와 다대일설정. 외래키 이름 = "product_id"
    private EventProduct eventProduct;

    @ManyToOne
    @JoinColumn(name = "member_id")            // Member 엔티티와 다대일설정. 외래키 이름 = "member_id"
    private Member member;

}
