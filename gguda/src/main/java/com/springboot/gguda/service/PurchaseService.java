package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.PurchaseDto;
import com.springboot.gguda.data.dto.PurchaseResponseDto;
import com.springboot.gguda.result.PurchaseResult;

import java.util.List;

public interface PurchaseService {

    PurchaseResult getPurchase(PurchaseDto purchaseDto);

    List<PurchaseResult> getPurchaseList(Long id);

    PurchaseResult getPurchaseDetail(Long id);
}
