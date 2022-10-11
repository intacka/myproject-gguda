package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.BasketResponseDto;
import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.data.entity.Basket;
import com.springboot.gguda.data.entity.Product;
import com.springboot.gguda.data.repository.BasketRepository;
import com.springboot.gguda.data.repository.MemberRepository;
import com.springboot.gguda.data.repository.ProductRepository;
import com.springboot.gguda.result.BasketResult;
import com.springboot.gguda.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketServiceImpl implements BasketService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final BasketRepository basketRepository;

    @Autowired
    public BasketServiceImpl(BasketRepository basketRepository,
                             ProductRepository productRepository,
                             MemberRepository memberRepository) {
        this.basketRepository = basketRepository;
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
    }


    @Override
    public BasketResponseDto addInBasket(Long productId, Long memberId, Long amount) {
        Basket basket = new Basket();
        basket.setProduct(productRepository.getById(productId));
        basket.setMember(memberRepository.getById(memberId));
        basket.setAmount(amount);
        basket.setMedianPrice(productRepository.getById(productId).getPrice()*amount);

        basketRepository.save(basket);

        BasketResponseDto basketResponseDto = new BasketResponseDto();
        basketResponseDto.setId(basket.getId());
        basketResponseDto.setAmount(basket.getAmount());
        basketResponseDto.setCreatedAt(basket.getCreatedAt());
        basketResponseDto.setUpdatedAt(basket.getUpdatedAt());
        basketResponseDto.setProductId(basket.getProduct().getId());
        basketResponseDto.setMemberId(basket.getMember().getId());
        basketResponseDto.setMedianPrice(basket.getMedianPrice());


        return basketResponseDto;
    }

    @Override
    public List<BasketResult> getBasketProductList(Long memberId) {
        List<Basket> baskets = basketRepository.findAllByMemberId(memberId);

        List<BasketResult> basketResultList = new ArrayList<>();
        Long amount;
        Long medianPrice;
        for(Basket basket : baskets){
            Product product = productRepository.getById(basket.getProduct().getId());// productId 구했지.
            ProductResponseDto dto = ProductResponseDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .sales(product.getSales())
                    .stock(product.getStock())
                    .salesType(product.getSalesType())
                    .brand(product.getBrand())
                    .createdAt(product.getCreatedAt())
                    .updatedAt(product.getUpdatedAt())
                    .build();
            amount = basket.getAmount();
            medianPrice = product.getPrice()*amount;
            //하나로 묶어서 add해야한다.
            basketResultList.add(new BasketResult(dto, amount, medianPrice));
        }

        return basketResultList;
    }

    @Override
    public Long getTotalPrice(Long memberId) {
        List<Basket> baskets = basketRepository.findAllByMemberId(memberId);

        Long totalPrice = 0L;

        for(Basket basket : baskets){
            totalPrice += basket.getMedianPrice();
        }

        return totalPrice;
    }

    @Override
    public BasketResponseDto putBasket(Long productId, Long memberId, Long amount) {
        Basket basket = basketRepository.getByProductIdAndMemberId(productId, memberId);

        basket.setAmount(amount);
        basket.setMedianPrice(productRepository.getById(productId).getPrice()*amount);

        basketRepository.save(basket);

        BasketResponseDto basketResponseDto = new BasketResponseDto();
        basketResponseDto.setId(basket.getId());
        basketResponseDto.setAmount(basket.getAmount());
        basketResponseDto.setCreatedAt(basket.getCreatedAt());
        basketResponseDto.setUpdatedAt(basket.getUpdatedAt());
        basketResponseDto.setProductId(basket.getProduct().getId());
        basketResponseDto.setMemberId(basket.getMember().getId());
        basketResponseDto.setMedianPrice(basket.getMedianPrice());


        return basketResponseDto;
    }

    @Override
    public boolean deleteBasketProduct(Long id) {
        basketRepository.deleteById(id);

        basketRepository.deleteByProductId(id);

        return true;
    }
}
