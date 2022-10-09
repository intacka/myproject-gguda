package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.ShippingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingInfoRepository extends JpaRepository<ShippingInfo, Long> {

    ShippingInfo getByPurchaseId(Long id);
}
