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
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // 고유 id

    @Column(name = "title")
    private String title;                           // 제목

    @Column(columnDefinition = "TEXT", name = "content")
    private String content;                     // 내용

    @Column(name = "private_whether")
    private Integer privateWhether;            // 비밀여부 - 0:No, 1:Yes

    @Column(name = "reg_date")
    private Date regDate; // 등록날짜

    @ManyToOne
    @JoinColumn(name = "product_id")            // Product 엔티티와 다대일설정. 외래키 이름 = "product_id"
    private Product product;

    @ManyToOne
    @JoinColumn(name = "member_id")            // Product 엔티티와 다대일설정. 외래키 이름 = "product_id"
    private Member member;

}
