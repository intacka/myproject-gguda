package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.Purchase;
import com.springboot.gguda.data.entity.PurchaseProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseProductInfoRepository extends JpaRepository<PurchaseProductInfo, Long> {

}
