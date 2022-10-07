package com.springboot.gguda.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "event_question_answer")
public class EventQuestionAnswer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // 고유 id

    @Column(columnDefinition = "TEXT", name = "content")
    private String content;                     // 내용

    @Column(name = "private_whether")
    private Integer privateWhether;            // 비밀여부 - 0:No, 1:Yes

    @ManyToOne
    @JoinColumn(name = "event_question_id")
    private EventQuestion eventQuestion;

}
