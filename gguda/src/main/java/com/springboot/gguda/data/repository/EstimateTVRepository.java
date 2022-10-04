package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.apply.EstimateElec;
import com.springboot.gguda.data.entity.apply.EstimateTV;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstimateTVRepository extends JpaRepository<EstimateTV, Long> {

}
