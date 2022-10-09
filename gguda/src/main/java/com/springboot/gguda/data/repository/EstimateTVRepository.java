package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.apply.EstimateElec;
import com.springboot.gguda.data.entity.apply.EstimateTV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstimateTVRepository extends JpaRepository<EstimateTV, Long> {
    List<EstimateTV> findAllByMemberIdOrderByCreatedAtDesc(Long memberId);
}
