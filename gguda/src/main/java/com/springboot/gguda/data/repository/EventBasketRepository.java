package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.Basket;
import com.springboot.gguda.data.entity.EventBasket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventBasketRepository extends JpaRepository<EventBasket, Long> {

    List<EventBasket> findAllByMemberId(Long memberId);

    EventBasket getByEventProductIdAndMemberId(Long eventProductId, Long memberId);
}
