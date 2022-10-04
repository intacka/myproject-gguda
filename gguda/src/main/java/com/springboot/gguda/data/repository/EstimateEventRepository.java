package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.apply.EstimateEvent;
import com.springboot.gguda.data.entity.apply.EstimateTV;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstimateEventRepository extends JpaRepository<EstimateEvent, Long> {

}
