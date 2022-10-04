package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.EventProduct;
import com.springboot.gguda.data.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventProductRepository extends JpaRepository<EventProduct, Long> {


    Page<EventProduct> findAllBy(Pageable pageable);
    Page<EventProduct> findAllBySort(String sort, Pageable pageable);

}
