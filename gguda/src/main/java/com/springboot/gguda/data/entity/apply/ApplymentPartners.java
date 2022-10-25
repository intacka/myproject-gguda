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
    @Column(name = "business_name")
    private String businessName;
    @Column(name = "business_num")
    private String businessNum;
    @Column(name = "representative")
    private String representative;
    @Column(name = "business_address")
    private String businessAddress;

    @Column(name = "service_name")
    private String serviceName;
    @Column(name = "site_address")
    private String siteAddress;
    @Column(name = "store_location", columnDefinition = "TEXT")
    private String storeLocation;
    @Column(name = "category", columnDefinition = "TEXT")
    private String category;
    @Column(name = "deal_method")
    private String dealMethod;

    private String name;
    @Column(name = "phone_num")
    private String phoneNum;
    @Column(name = "gguda_id")
    private String ggudaId;
    @Column(name = "admin_id")
    private String adminId;
    @Column(name = "etc_content", columnDefinition = "TEXT")
    private String etcContent;
    @Column(name = "email")
    private String email;
    @Column(name = "tax_email")
    private String taxEmail;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
