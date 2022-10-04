package com.springboot.gguda.data.entity.apply;

import com.springboot.gguda.data.entity.BaseEntity;
import com.springboot.gguda.data.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "applyment_partners")
public class ApplymentPartners extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String businessName;
    private String businessNum;
    private String representative;
    private String businessAddress;

    private String serviceName;
    private String siteAddress;
    @Column(name = "store_location", columnDefinition = "TEXT")
    private String storeLocation;
    @Column(name = "category", columnDefinition = "TEXT")
    private String category;
    @Column(name = "deal_method")
    private String dealMethod;

    private String name;
    private String phoneNum;
    private String ggudaId;
    private String adminId;
    @Column(name = "etc_content", columnDefinition = "TEXT")
    private String etcContent;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
