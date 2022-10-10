package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.*;
import com.springboot.gguda.data.entity.*;
import com.springboot.gguda.data.repository.*;
import com.springboot.gguda.result.PurchaseResult;
import com.springboot.gguda.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final CouponRepository couponRepository;
    private final ReserveHistoryRepository reserveHistoryRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ShippingInfoRepository shippingInfoRepository;
    private final PurchaseProductInfoRepository purchaseProductInfoRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository,
                               CouponRepository couponRepository,
                               ReserveHistoryRepository reserveHistoryRepository,
                               MemberRepository memberRepository,
                               ProductRepository productRepository,
                               ShippingInfoRepository shippingInfoRepository,
                               PurchaseProductInfoRepository purchaseProductInfoRepository) {
        this.purchaseRepository = purchaseRepository;
        this.couponRepository = couponRepository;
        this.reserveHistoryRepository = reserveHistoryRepository;
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
        this.shippingInfoRepository = shippingInfoRepository;
        this.purchaseProductInfoRepository = purchaseProductInfoRepository;
    }


    @Override
    public PurchaseResult getPurchase(PurchaseDto purchaseDto) {
        Purchase purchase = new Purchase();
        purchase.setHowPay(purchaseDto.getHowPay());
        purchase.setCouponId(purchaseDto.getCouponId());
        purchase.setUseReserve(purchaseDto.getUseReserve());
        purchase.setState(purchaseDto.getState());
        purchase.setTotalPrice(purchaseDto.getTotalPrice());
        //////////
        List<PurchaseProductInfo> pPinfos = new ArrayList<>();
        List<PurchaseProductInfoDto> pPinfoDtos = purchaseDto.getPurchaseProductInfoDtos();
        for (PurchaseProductInfoDto pPinfoDto : pPinfoDtos) {
            PurchaseProductInfo purchaseProductInfo = new PurchaseProductInfo();

            purchaseProductInfo.setProductId(pPinfoDto.getProductId());
            purchaseProductInfo.setAmount(pPinfoDto.getAmount());
            purchaseProductInfoRepository.save(purchaseProductInfo);
            System.out.println(purchaseProductInfo);// amount가 자꾸 null값으로 들어옴
            pPinfos.add(purchaseProductInfo);
        }

        purchase.setPurchaseProductInfos(pPinfos);
        ///////////
        purchase.setMember(memberRepository.getById(purchaseDto.getMemberId()));

        purchaseRepository.save(purchase);//////////////////오류지점

        ShippingInfo shippingInfo = new ShippingInfo();
        shippingInfo.setName(purchaseDto.getName());
        shippingInfo.setAddress(purchaseDto.getAddress());
        shippingInfo.setPhoneNum(purchaseDto.getPhoneNum());
        shippingInfo.setReq(purchaseDto.getReq());
        shippingInfo.setPurchase(purchase);
        shippingInfoRepository.save(shippingInfo);

        PurchaseResponseDto purchaseResponseDto = new PurchaseResponseDto();
        purchaseResponseDto.setId(purchase.getId());
        purchaseResponseDto.setHowPay(purchase.getHowPay());
        purchaseResponseDto.setCouponId(purchase.getCouponId());
        purchaseResponseDto.setUseReserve(purchase.getUseReserve());
        purchaseResponseDto.setTotalPrice(purchase.getTotalPrice());
        purchaseResponseDto.setState(purchase.getState());
        purchaseResponseDto.setMemberId(purchase.getMember().getId());

        List<PurchaseProductInfoResponseDto> pPinfoResponseDtos = new ArrayList<>();
        for (PurchaseProductInfo pPinfo : pPinfos) {
            PurchaseProductInfoResponseDto purchaseProductInfoResponseDto = new PurchaseProductInfoResponseDto();
            purchaseProductInfoResponseDto.setProductId(pPinfo.getProductId());
            purchaseProductInfoResponseDto.setAmount(pPinfo.getAmount());
            pPinfoResponseDtos.add(purchaseProductInfoResponseDto);
        }
        purchaseResponseDto.setPurchaseProductInfoResponseDtos(pPinfoResponseDtos);

        purchaseResponseDto.setCreatedAt(purchase.getCreatedAt());
        purchaseResponseDto.setUpdatedAt(purchase.getUpdatedAt());

        ShippingInfoResponseDto shippingInfoResponseDto = new ShippingInfoResponseDto();
        shippingInfoResponseDto.setId(shippingInfo.getId());
        shippingInfoResponseDto.setName(shippingInfo.getName());
        shippingInfoResponseDto.setAddress(shippingInfo.getAddress());
        shippingInfoResponseDto.setPhoneNum(shippingInfo.getPhoneNum());
        shippingInfoResponseDto.setReq(shippingInfo.getReq());
        shippingInfoResponseDto.setPurchaseId(shippingInfo.getPurchase().getId());
        shippingInfoResponseDto.setCreatedAt(shippingInfo.getCreatedAt());
        shippingInfoResponseDto.setUpdatedAt(shippingInfo.getUpdatedAt());


        return new PurchaseResult(purchaseResponseDto, shippingInfoResponseDto);
    }

    @Override
    public List<PurchaseResult> getPurchaseList(Long id) {
        List<Purchase> purchases = purchaseRepository.getByMemberId(id); // 구매목록 List 다가져오게됨

        List<PurchaseResult> purchaseResults = new ArrayList<>();

        for(Purchase purchase:purchases) {
            ShippingInfo shippingInfo = shippingInfoRepository.getByPurchaseId(purchase.getId()); // 구매내역에 딸린 배송정보(1개)

            PurchaseResponseDto purchaseResponseDto = new PurchaseResponseDto();
            purchaseResponseDto.setId(purchase.getId());
            purchaseResponseDto.setHowPay(purchase.getHowPay());
            purchaseResponseDto.setCouponId(purchase.getCouponId());
            purchaseResponseDto.setUseReserve(purchase.getUseReserve());
            purchaseResponseDto.setTotalPrice(purchase.getTotalPrice());
            purchaseResponseDto.setState(purchase.getState());
            purchaseResponseDto.setMemberId(purchase.getMember().getId());
            //////////
            List<PurchaseProductInfo> pPinfos = purchase.getPurchaseProductInfos();
            List<PurchaseProductInfoResponseDto> pPinfoResponseDtos = new ArrayList<>();
            for (PurchaseProductInfo pPinfo : pPinfos) {
                PurchaseProductInfoResponseDto purchaseProductInfoResponseDto = new PurchaseProductInfoResponseDto();
                purchaseProductInfoResponseDto.setProductId(pPinfo.getProductId());
                purchaseProductInfoResponseDto.setAmount(pPinfo.getAmount());
                pPinfoResponseDtos.add(purchaseProductInfoResponseDto);
            }
            purchaseResponseDto.setPurchaseProductInfoResponseDtos(pPinfoResponseDtos);
            ///////////
            purchaseResponseDto.setCreatedAt(purchase.getCreatedAt());
            purchaseResponseDto.setUpdatedAt(purchase.getUpdatedAt());

            ShippingInfoResponseDto shippingInfoResponseDto = new ShippingInfoResponseDto();
            shippingInfoResponseDto.setId(shippingInfo.getId());
            shippingInfoResponseDto.setName(shippingInfo.getName());
            shippingInfoResponseDto.setAddress(shippingInfo.getAddress());
            shippingInfoResponseDto.setPhoneNum(shippingInfo.getPhoneNum());
            shippingInfoResponseDto.setReq(shippingInfo.getReq());
            shippingInfoResponseDto.setPurchaseId(shippingInfo.getPurchase().getId());
            shippingInfoResponseDto.setCreatedAt(shippingInfo.getCreatedAt());
            shippingInfoResponseDto.setUpdatedAt(shippingInfo.getUpdatedAt());

            purchaseResults.add(new PurchaseResult(purchaseResponseDto,shippingInfoResponseDto));
        }
    return purchaseResults;

    }

    @Override
    public PurchaseResult getPurchaseDetail(Long id) {
        Purchase purchase = purchaseRepository.getById(id);
        ShippingInfo shippingInfo = shippingInfoRepository.getByPurchaseId(id);

        PurchaseResponseDto purchaseResponseDto = new PurchaseResponseDto();
        purchaseResponseDto.setId(purchase.getId());
        purchaseResponseDto.setHowPay(purchase.getHowPay());
        purchaseResponseDto.setCouponId(purchase.getCouponId());
        purchaseResponseDto.setUseReserve(purchase.getUseReserve());
        purchaseResponseDto.setTotalPrice(purchase.getTotalPrice());
        purchaseResponseDto.setState(purchase.getState());
        purchaseResponseDto.setMemberId(purchase.getMember().getId());
        ////////// productIds 생성하고 세팅하기
        List<PurchaseProductInfo> pPinfos = purchase.getPurchaseProductInfos();
        List<PurchaseProductInfoResponseDto> pPinfoResponseDtos = new ArrayList<>();
        for (PurchaseProductInfo pPinfo : pPinfos) {
            PurchaseProductInfoResponseDto purchaseProductInfoResponseDto = new PurchaseProductInfoResponseDto();
            purchaseProductInfoResponseDto.setProductId(pPinfo.getProductId());
            purchaseProductInfoResponseDto.setAmount(pPinfo.getAmount());
            pPinfoResponseDtos.add(purchaseProductInfoResponseDto);
        }
        purchaseResponseDto.setPurchaseProductInfoResponseDtos(pPinfoResponseDtos);
        ///////////
        purchaseResponseDto.setCreatedAt(purchase.getCreatedAt());
        purchaseResponseDto.setUpdatedAt(purchase.getUpdatedAt());

        ShippingInfoResponseDto shippingInfoResponseDto = new ShippingInfoResponseDto();
        shippingInfoResponseDto.setId(shippingInfo.getId());
        shippingInfoResponseDto.setName(shippingInfo.getName());
        shippingInfoResponseDto.setAddress(shippingInfo.getAddress());
        shippingInfoResponseDto.setPhoneNum(shippingInfo.getPhoneNum());
        shippingInfoResponseDto.setReq(shippingInfo.getReq());
        shippingInfoResponseDto.setPurchaseId(shippingInfo.getPurchase().getId());
        shippingInfoResponseDto.setCreatedAt(shippingInfo.getCreatedAt());
        shippingInfoResponseDto.setUpdatedAt(shippingInfo.getUpdatedAt());

        return new PurchaseResult(purchaseResponseDto, shippingInfoResponseDto);
    }
}
