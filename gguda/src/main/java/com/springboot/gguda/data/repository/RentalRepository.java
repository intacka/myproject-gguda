package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.Purchase;
import com.springboot.gguda.data.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findAllByMemberId(Long memberId);

    List<Rental> findAllByOrderByCreatedAtDesc();
}
