package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.BasketResponseDto;
import com.springboot.gguda.data.dto.EventBasketResponseDto;
import com.springboot.gguda.data.dto.EventProductResponseDto;
import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.data.entity.Basket;
import com.springboot.gguda.data.entity.EventBasket;
import com.springboot.gguda.data.entity.EventProduct;
import com.springboot.gguda.data.entity.Product;
import com.springboot.gguda.data.repository.*;
import com.springboot.gguda.result.BasketResult;
import com.springboot.gguda.result.EventBasketResult;
import com.springboot.gguda.service.BasketService;
import com.springboot.gguda.service.EventBasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventBasketServiceImpl implements EventBasketService {

    private final EventProductRepository eventProductRepository;
    private final MemberRepository memberRepository;
    private final EventBasketRepository eventBasketRepository;

    @Autowired
    public EventBasketServiceImpl(EventBasketRepository eventBasketRepository,
                                  EventProductRepository eventProductRepository,
                                  MemberRepository memberRepository) {
        this.eventBasketRepository = eventBasketRepository;
        this.memberRepository = memberRepository;
        this.eventProductRepository = eventProductRepository;
    }


    @Override
    public EventBasketResponseDto addInEventBasket(Long eventProductId, Long memberId, Long amount) {
        EventBasket eventBasket = new EventBasket();
        eventBasket.setEventProduct(eventProductRepository.getById(eventProductId));
        eventBasket.setMember(memberRepository.getById(memberId));
        eventBasket.setAmount(amount);

        eventBasketRepository.save(eventBasket);

        EventBasketResponseDto eventBasketResponseDto = new EventBasketResponseDto();
        eventBasketResponseDto.setId(eventBasket.getId());
        eventBasketResponseDto.setAmount(eventBasket.getAmount());
        eventBasketResponseDto.setCreatedAt(eventBasket.getCreatedAt());
        eventBasketResponseDto.setUpdatedAt(eventBasket.getUpdatedAt());
        eventBasketResponseDto.setEventProductId(eventBasket.getEventProduct().getId());
        eventBasketResponseDto.setMemberId(eventBasket.getMember().getId());


        return eventBasketResponseDto;
    }

    @Override
    public List<EventBasketResult> getEventBasketEventProductList(Long memberId) {
        List<EventBasket> eventBaskets = eventBasketRepository.findAllByMemberId(memberId);

        List<EventBasketResult> eventBasketResultList = new ArrayList<>();
        Long amount;
        for(EventBasket eventBasket : eventBaskets){
            EventProduct eventProduct = eventProductRepository.getById(eventBasket.getEventProduct().getId());// productId 구했지.
            EventProductResponseDto dto = EventProductResponseDto.builder()
                    .id(eventProduct.getId())
                    .name(eventProduct.getName())
                    .salesType(eventProduct.getSalesType())
                    .sort(eventProduct.getSort())
                    .createdAt(eventProduct.getCreatedAt())
                    .updatedAt(eventProduct.getUpdatedAt())
                    .build();
            amount = eventBasket.getAmount();
            //하나로 묶어서 add해야한다.
            eventBasketResultList.add(new EventBasketResult(dto, amount));
        }

        return eventBasketResultList;
    }

    @Override
    public EventBasketResponseDto putEventBasket(Long eventProductId, Long memberId, Long amount) {
        EventBasket eventBasket = eventBasketRepository.getByEventProductIdAndMemberId(eventProductId, memberId);

        eventBasket.setAmount(amount);
        eventBasketRepository.save(eventBasket);

        EventBasketResponseDto eventBasketResponseDto = new EventBasketResponseDto();
        eventBasketResponseDto.setId(eventBasket.getId());
        eventBasketResponseDto.setAmount(eventBasket.getAmount());
        eventBasketResponseDto.setCreatedAt(eventBasket.getCreatedAt());
        eventBasketResponseDto.setUpdatedAt(eventBasket.getUpdatedAt());
        eventBasketResponseDto.setEventProductId(eventBasket.getEventProduct().getId());
        eventBasketResponseDto.setMemberId(eventBasket.getMember().getId());


        return eventBasketResponseDto;
    }

    @Override
    public boolean deleteEventBasketEventProduct(Long id) {
        eventBasketRepository.deleteById(id);
        return true;
    }
}
