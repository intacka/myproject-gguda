package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.BasketResponseDto;
import com.springboot.gguda.data.dto.EventBasketResponseDto;
import com.springboot.gguda.data.dto.EventProductResponseDto;
import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.result.BasketResult;
import com.springboot.gguda.result.EventBasketResult;

import java.util.List;

public interface EventBasketService {


    EventBasketResponseDto addInEventBasket(Long eventProductId, Long memberId, Long amount);

    List<EventBasketResult> getEventBasketEventProductList(Long memberId);

    EventBasketResponseDto putEventBasket(Long eventProductId, Long memberId, Long amount);

    boolean deleteEventBasketEventProduct(Long id);
}
