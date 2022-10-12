package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.PurchaseDto;
import com.springboot.gguda.data.dto.PurchaseProductInfoResponseDto;
import com.springboot.gguda.data.dto.PurchaseResponseDto;
import com.springboot.gguda.data.repository.CouponRepository;
import com.springboot.gguda.result.PurchaseResult;
import com.springboot.gguda.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final CouponService couponService;
    private final BasketService basketService;
    private final ProductService productService;
    private final ReserveHistoryService reserveHistoryService;
    private final CouponRepository couponRepository;
    @Autowired
    public PurchaseController(PurchaseService purchaseService,
                              CouponService couponService,
                              BasketService basketService,
                              ProductService productService,
                              ReserveHistoryService reserveHistoryService,
                              CouponRepository couponRepository) {
        this.purchaseService = purchaseService;
        this.couponService = couponService;
        this.basketService = basketService;
        this.productService = productService;
        this.reserveHistoryService = reserveHistoryService;
        this.couponRepository = couponRepository;
    }




    @GetMapping(value = "/purchase-history/view")     // 회원id로 final 구매내역모두 (List) return  API
    public List<PurchaseResult> getPurchaseList(Long id) {
        List<PurchaseResult> purchaseResults = purchaseService.getPurchaseList(id);

        return purchaseResults;
    }

    @GetMapping(value = "/purchase-history/view-all")     // 구매내역 고유id로 final 구매내역 Detail return  API
    public PurchaseResult getPurchase(Long id) {
        PurchaseResult purchaseResult = purchaseService.getPurchaseDetail(id);

        return purchaseResult;
    }

    // 결제할 총 금액 return (구매직전)
    @PostMapping(value = "/total-price/view")
    public Double getTotalPrice(Long basketTotalPrice, Long couponId, Long reserve) {
        Double couponDiscountRate;

        if (reserve == null) {
            reserve = 0L;
        }

        if (couponId!=null) {
            couponDiscountRate = couponService.getCouponDiscountRate(couponId);
        } else {
            couponDiscountRate = 0.0;
        }

        Double totalPrice = (basketTotalPrice-(basketTotalPrice*couponDiscountRate)-reserve);
        return totalPrice;
    }

    @PostMapping(value = "/final-pay")  //  최종결제 API
    public PurchaseResult createPurchase(@RequestBody PurchaseDto purchaseDto) { // 입력받는문제해결해야함
        PurchaseResult purchaseResult = purchaseService.getPurchase(purchaseDto);
        PurchaseResponseDto purchaseResponseDto = purchaseResult.getPurchaseResponseDto();

        ////  장바구니에서 삭제하기
        List<PurchaseProductInfoResponseDto> pPinfoResponseDtos = purchaseResponseDto.getPurchaseProductInfoResponseDtos();
        for(PurchaseProductInfoResponseDto pPinfoResponseDto : pPinfoResponseDtos) {
            basketService.deleteBasketProduct((long)pPinfoResponseDto.getProductId());  // 장바구니 삭제

            Long productId = (long)pPinfoResponseDto.getProductId();
            Integer amount = pPinfoResponseDto.getAmount();
            productService.putStock(productId, amount);                 // 재고량 변동(재고0이면 품절로 전환), 판매량증가
        }
        // 주문목록은 이미 Purchase에 member로 연관관계매핑이 되어있다. 그러므로 주문목록에는 자동으로 추가가된것이다

        //// 해당쿠폰을 사용상태로 변경
        if (purchaseResponseDto.getCouponId() != null &&
                couponRepository.getById(purchaseResponseDto.getCouponId()).getIsUsed() == 0) {
            couponRepository.getById(purchaseResponseDto.getCouponId()).setIsUsed(1);
        }

        //// 적립금 내역추가, 적립금 변동 - 1%
        Integer reservePrice = (int) (purchaseResponseDto.getTotalPrice() * 0.01); // 적립금액
        reserveHistoryService.createPurchaseReserveHistory(reservePrice, purchaseResponseDto.getMemberId());

        return purchaseResult;
    }




}
