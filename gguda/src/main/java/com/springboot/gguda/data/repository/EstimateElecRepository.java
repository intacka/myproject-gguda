package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.Product;
import com.springboot.gguda.data.entity.apply.ApplymentPartners;
import com.springboot.gguda.data.entity.apply.EstimateElec;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstimateElecRepository extends JpaRepository<EstimateElec, Long> {
    List<EstimateElec> findAll(Sort sort);

    List<EstimateElec> findAllByMemberIdOrderByCreatedAtDesc(Long memberId);
}
