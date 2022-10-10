package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.Basket;
import com.springboot.gguda.data.entity.Member;
import com.springboot.gguda.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    List<Basket> findAllByMemberId(Long memberId);

    Basket getByProductIdAndMemberId(Long productId, Long memberId);

    void deleteByProductId(Long id);
}
