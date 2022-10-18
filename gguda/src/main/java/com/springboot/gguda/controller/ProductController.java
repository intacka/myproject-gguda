package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.ProductDto;
import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    @PostMapping(value = "/register") //        상품등록하기
    public ResponseEntity<ProductResponseDto> createProduct(
            ProductDto productDto,
            MultipartFile file) throws IOException {

        ProductResponseDto productResponseDto = productService.saveProductDto(productDto, file);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PutMapping(value = "/put") //              상품수정하기
    public ResponseEntity<ProductResponseDto> putProduct(@RequestBody ProductDto productDto, Long id) {
        ProductResponseDto productResponseDto = productService.putProduct(productDto, id);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PostMapping(value = "/delete")         //  상품삭제하기
    public ProductResponseDto dropProduct(Long id) {

        ProductResponseDto productResponseDto = productService.deleteProduct(id);

        return productResponseDto;
    }

}
