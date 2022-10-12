package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.BasketResponseDto;
import com.springboot.gguda.data.repository.CouponRepository;
import com.springboot.gguda.result.BasketResult;
import com.springboot.gguda.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/basket")
public class BasketController {
    private final BasketService basketService;
    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    // 장바구니에 추가하기
    @PostMapping(value = "/addtion")
    public ResponseEntity<BasketResponseDto> addProductInBasket(Long productId, Long memberId, Long amount) {
        BasketResponseDto basketResponseDto = basketService.addInBasket(productId, memberId, amount);

        return ResponseEntity.status(HttpStatus.OK).body(basketResponseDto);
    }

    // 장바구니에 담긴 Product List return하기
    @GetMapping(value = "/view-list")
    public List<BasketResult> getBasketProductList(Long memberId) {
        List<BasketResult> basketResultList = basketService.getBasketProductList(memberId);

        return basketResultList;
    }

    // 일반상품 장바구니에 있는 총가격 return
    @PostMapping(value="/view/total-price")
    public Long getTotalPrice(Long memberId) {
        Long totalPrice = basketService.getTotalPrice(memberId);
        return totalPrice;
    }

    // 장바구니 수량수정
    @PutMapping(value="/put")
    public ResponseEntity<BasketResponseDto> putBasket(Long productId, Long memberId, Long amount) {
        BasketResponseDto basketResponseDto = basketService.putBasket(productId, memberId, amount);

        return ResponseEntity.status(HttpStatus.OK).body(basketResponseDto);
    }

    // 장바구니 목록 삭제 (장바구니id줘야함)
    @DeleteMapping(value = "/basket-product/delete")
    public boolean deleteBasketProduct(Long id) {
        boolean result = basketService.deleteBasketProduct(id);
        return result;
    }

}
