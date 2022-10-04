package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.CouponAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponAdminRepository extends JpaRepository<CouponAdmin, Long> {

    List<CouponAdmin> findAllByMemberIdOrderByCreatedAtDesc(Long id);
}
