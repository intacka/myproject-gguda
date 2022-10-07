package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.BasketResponseDto;
import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.data.dto.ReviewDto;
import com.springboot.gguda.data.dto.ReviewResponseDto;
import com.springboot.gguda.result.BasketResult;

import java.util.List;

public interface BasketService {


    BasketResponseDto addInBasket(Long productId, Long memberId, Long amount);

    List<BasketResult> getBasketProductList(Long memberId);

    Long getTotalPrice(Long memberId);

    BasketResponseDto putBasket(Long productId, Long memberId, Long amount);
}
