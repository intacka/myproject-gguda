package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.Product;
import com.springboot.gguda.data.entity.apply.ApplymentPartners;
import com.springboot.gguda.data.entity.apply.EstimateElec;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplymentPartnersRepository extends JpaRepository<ApplymentPartners, Long> {

    List<ApplymentPartners> findAll(Sort sort);

}
