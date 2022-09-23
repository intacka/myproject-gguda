package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.ProductDto;
import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.data.entity.Product;
import com.springboot.gguda.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/main")
public class AllController {

    private final ProductService productService;

    @Autowired
    public AllController(ProductService productService) {
        this.productService = productService;
    }


    ///////////////////////////////////////////////////////////////////////
    //              메인페이지 API                       //


    @GetMapping(value = "/popular") // 가장 인기있는(판매량이 많은) 상품 10개 불러오기
    public List<ProductResponseDto> getMainPage(){
        List<ProductResponseDto> popularTop10 = productService.getPopularTop10Product();
        return popularTop10;
    }


    @GetMapping(value = "/macbook") // 맥북에 해당되는 상품 10개 불러오기
    public List<ProductResponseDto> getMacPage(){
        List<ProductResponseDto> macbook10 = productService.getTop10Product("맥북");
        return macbook10;
    }


    @GetMapping(value = "/ipad") // 아이패드에 해당되는 상품 10개 불러오기
    public List<ProductResponseDto> getIpadPage(){
        List<ProductResponseDto> ipad10 = productService.getTop10Product("아이패드");
        return ipad10;
    }

    @GetMapping(value = "/samsung-notebook") // 삼성 노트북에 해당되는 상품 10개 불러오기
    public List<ProductResponseDto> getSamsungNotebookPage(){
        List<ProductResponseDto> samsungNotebook10 = productService.getTop10Product("삼성 노트북");
        return samsungNotebook10;
    }

    @GetMapping(value = "/samsung-pc") // 삼성 데스크탑에 해당되는 상품 10개 불러오기
    public List<ProductResponseDto> getSamsungPcPage(){
        List<ProductResponseDto> samsungPc = productService.getTop10Product("삼성 PC");
        return samsungPc;
    }

    @GetMapping(value = "/benQ") // 벤큐 모니터에 해당되는 상품 10개 불러오기
    public List<ProductResponseDto> getBenQMonitorPage(){
        List<ProductResponseDto> BenQMonitor = productService.getTop10Product("모니터", "벤큐");
        return BenQMonitor;
    }


    /////////////////////////////////////////////////////////////////////////////////////

    @GetMapping(value = "/product/{product_name}") // 디테일페이지에 해당하는 상품정보 불러오기
    public ResponseEntity<ProductResponseDto> getProduct(String product_name) {
        ProductResponseDto productResponseDto = productService.getProduct(product_name);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    // 후기, 질문, 연관상품 리턴하는 API도 작성해야한다.


    @PostMapping(value = "register") // 상품등록하기
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto) {
        ProductResponseDto productResponseDto = productService.saveProductDto(productDto);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

}

