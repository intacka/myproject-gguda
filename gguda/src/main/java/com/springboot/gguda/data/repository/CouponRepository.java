package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    List<Coupon> findAllByMemberIdAndIsUsedOrderByCreatedAtDesc(String memberId, Integer isUsed);
//    List<Coupon> findAllByMemberIdOrderByCreatedAtDesc(String memberId);
    Coupon findByNum(Long num);


}
