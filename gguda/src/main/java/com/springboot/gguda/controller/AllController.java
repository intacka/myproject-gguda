package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.*;
import com.springboot.gguda.result.DetailResult;
import com.springboot.gguda.result.MainResult;
import com.springboot.gguda.service.MemberService;
import com.springboot.gguda.service.ProductService;
import com.springboot.gguda.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/main")
public class AllController {

    private final ProductService productService;
    private final QuestionService questionService;
    private final MemberService memberService;

    @Autowired
    public AllController(ProductService productService, QuestionService questionService, MemberService memberService) {
        this.productService = productService;
        this.questionService = questionService;
        this.memberService = memberService;
    }


    ///////////////////////////////////////////////////////////////////////

    //              메인페이지 API                       //
    @GetMapping(value = "/main")    // 메인페이지 API
    public MainResult getMainPage(){
        List<ProductResponseDto> popular = productService.getPopularTop10Product();
        List<ProductResponseDto> macbook10 = productService.getTop10Product("맥북");
        List<ProductResponseDto> ipad10 = productService.getTop10Product("아이패드");
        List<ProductResponseDto> samsungNotebook10 = productService.getTop10Product("삼성 노트북");
        List<ProductResponseDto> samsungPc = productService.getTop10Product("삼성 PC");
        List<ProductResponseDto> BenQMonitor = productService.getTop10Product("모니터", "벤큐");

        return new MainResult(popular, macbook10, ipad10, samsungNotebook10, samsungPc, BenQMonitor);
    }

    @GetMapping(value = "/product/{product_name}") // 디테일페이지에 해당하는 상품정보 불러오기
    public DetailResult getProduct(String product_name) {
        ProductResponseDto productResponseDto = productService.getProduct(product_name);
        List<QuestionResponseDto> questionResponseDto = productService.getQuestion(productResponseDto.getId());
        // 그상품의 id에 해당하는 질문리스트 가져오기

        return new DetailResult(productResponseDto, questionResponseDto);
    }

    // 후기, 질문, 연관상품 리턴하는 API도 작성해야한다.


    @PostMapping(value = "register/product") // 상품등록하기
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto) {
        ProductResponseDto productResponseDto = productService.saveProductDto(productDto);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PostMapping(value = "register/question") // 질문등록하기//////////////////////////////////////////////////////////
    public ResponseEntity<QuestionResponseDto> createQuestion(@RequestBody QuestionDto questionDto) {
        QuestionResponseDto questionResponseDto = questionService.saveQuestionDto(questionDto);

        return ResponseEntity.status(HttpStatus.OK).body(questionResponseDto);
    }

    @PostMapping(value = "register/member") // 회원 만들기 /////
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody MemberDto memberDto) {
        MemberResponseDto memberResponseDto = memberService.saveMemberDto(memberDto);

        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDto);
    }


//    @GetMapping(value = "/popular") // 가장 인기있는(판매량이 많은) 상품 10개 불러오기
//    public List<ProductResponseDto> getMainPage(){
//        List<ProductResponseDto> popularTop10 = productService.getPopularTop10Product();
//        return popularTop10;
//    }
//
//
//    @GetMapping(value = "/macbook") // 맥북에 해당되는 상품 10개 불러오기
//    public List<ProductResponseDto> getMacPage(){
//        List<ProductResponseDto> macbook10 = productService.getTop10Product("맥북");
//        return macbook10;
//    }
//
//
//    @GetMapping(value = "/ipad") // 아이패드에 해당되는 상품 10개 불러오기
//    public List<ProductResponseDto> getIpadPage(){
//        List<ProductResponseDto> ipad10 = productService.getTop10Product("아이패드");
//        return ipad10;
//    }
//
//    @GetMapping(value = "/samsung-notebook") // 삼성 노트북에 해당되는 상품 10개 불러오기
//    public List<ProductResponseDto> getSamsungNotebookPage(){
//        List<ProductResponseDto> samsungNotebook10 = productService.getTop10Product("삼성 노트북");
//        return samsungNotebook10;
//    }
//
//    @GetMapping(value = "/samsung-pc") // 삼성 데스크탑에 해당되는 상품 10개 불러오기
//    public List<ProductResponseDto> getSamsungPcPage(){
//        List<ProductResponseDto> samsungPc = productService.getTop10Product("삼성 PC");
//        return samsungPc;
//    }
//
//    @GetMapping(value = "/benQ") // 벤큐 모니터에 해당되는 상품 10개 불러오기
//    public List<ProductResponseDto> getBenQMonitorPage(){
//        List<ProductResponseDto> BenQMonitor = productService.getTop10Product("모니터", "벤큐");
//        return BenQMonitor;

//    }


    /////////////////////////////////////////////////////////////////////////////////////

}

