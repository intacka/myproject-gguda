package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.Rental;
import com.springboot.gguda.data.entity.RentalStopReq;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalStopReqRepository extends JpaRepository<RentalStopReq, Long> {
    List<RentalStopReq> findAllByOrderByCreatedAtDesc();
}
