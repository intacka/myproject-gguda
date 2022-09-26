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
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // 고유 id

    @Column(columnDefinition = "TEXT", name = "content")
    private String content;                     // 내용

    @Column(name = "stars")
    private Integer stars;                      // 평점

    @Column(name = "reg_date")                  // 등록 날짜
    private Date regDate;

    @ManyToOne
    @JoinColumn(name = "product_id")            // Product 엔티티와 다대일설정. 외래키 이름 = "product_id"
    private Product product;

    @ManyToOne
    @JoinColumn(name = "member_id")            // Member 엔티티와 다대일설정. 외래키 이름 = "member_id"
    private Member member;

}
