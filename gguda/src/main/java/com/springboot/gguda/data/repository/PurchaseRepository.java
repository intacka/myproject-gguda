package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> getByMemberId(Long memberId);
}
