package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.data.dto.QuestionAnswerResponseDto;
import com.springboot.gguda.data.dto.QuestionResponseDto;
import com.springboot.gguda.data.dto.ReviewResponseDto;
import com.springboot.gguda.result.DetailResult;
import com.springboot.gguda.result.MainResult;
import com.springboot.gguda.service.ApplymentPartnersService;
import com.springboot.gguda.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/page-product")
public class PageController {

    private final ProductService productService;


    @Autowired
    public PageController(ProductService productService) {
        this.productService = productService;
    }


    //   메인페이지 API
    @GetMapping(value = "/main-page")
    public MainResult getMainPage(){
        List<ProductResponseDto> popular = productService.getPopularTop10Product();
        List<ProductResponseDto> macbook10 = productService.getTop10Product("맥북");
        List<ProductResponseDto> ipad10 = productService.getTop10Product("아이패드");
        List<ProductResponseDto> samsungNotebook10 = productService.getTop10Product("노트북", "삼성전자");
        List<ProductResponseDto> samsungPc = productService.getTop10Product("PC", "삼성전자");
        List<ProductResponseDto> BenQMonitor = productService.getTop10Product("모니터", "벤큐");

        return new MainResult(popular, macbook10, ipad10, samsungNotebook10, samsungPc, BenQMonitor);
    }

    // 디테일페이지에 해당하는 상품정보 불러오기
    @GetMapping(value = "/detail-page")
    public DetailResult getProduct(Long id) {
        ProductResponseDto productResponseDto = productService.getProduct(id);
        // 상품정보 가져오기
        List<QuestionResponseDto> questionResponseDtos = productService.getQuestion(id);
        List<QuestionAnswerResponseDto> questionAnswerResponseDtos = productService.getQuestionAnswer(id);
        // 상품에 해당하는 질문리스트, 답변리스트 가져오기
        List<ReviewResponseDto> reviewResponseDtos = productService.getReview(id);
        // 상품에 해당하는 후기리스트 가져오기

        return new DetailResult(productResponseDto, questionResponseDtos, questionAnswerResponseDtos, reviewResponseDtos);
    }

    //   0원렌탈,브랜드 API
    @GetMapping(value = "/zero-rental-page")
    public List<ProductResponseDto> getzeroCardPage(){
        List<ProductResponseDto> zero = productService.getZeroRental(600000);

        return zero;
    }

    // 브랜드 API (전체상품목록 가져오기)
    @GetMapping(value = "/brand-all")
    public List<ProductResponseDto> getAllProduct() {
        List<ProductResponseDto> all = productService.getAllProduct();

        return all;
    }

    // 브랜드별로 상품 리스트 가져오기
    @GetMapping(value = "/brand/{brand_name}")
    public List<ProductResponseDto> getByBrand(String brand_name) {

        List<ProductResponseDto> brandProduct = productService.getProductListByBrand(brand_name);

        return brandProduct;
    }

    // 인기있는 노트북 10개 가져오기
    @GetMapping(value = "/popular-notebook-products")
    public List<ProductResponseDto> getPopularNotebook(){
        List<ProductResponseDto> samsungNotebook10 = productService.getTop10Product("노트북");

        return samsungNotebook10;
    }



    /////////////////////////////////////////////////////////////////////////





}

