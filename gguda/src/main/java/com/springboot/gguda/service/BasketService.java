package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.BasketResponseDto;
import com.springboot.gguda.data.entity.Basket;
import com.springboot.gguda.result.BasketResult;

import java.util.List;
import java.util.Optional;

public interface BasketService {


    BasketResponseDto addInBasket(Long productId, Long memberId, Long amount);

    List<BasketResult> getBasketProductList(Long memberId);

    Long getTotalPrice(Long memberId);

    BasketResponseDto putBasket(Long productId, Long memberId, Long amount);

    boolean deleteBasketProduct(Long id);
}
