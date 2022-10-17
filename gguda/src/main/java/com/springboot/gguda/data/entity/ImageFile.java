package com.springboot.gguda.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Table(name = "image_file")
public class ImageFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 상품고유번호

    @Column(name = "filename")
    private String filename;

    @Column(name = "filepath")
    private String filepath;

}
