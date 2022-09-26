package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.ProductDto;
import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.data.dto.QuestionResponseDto;
import com.springboot.gguda.data.dto.ReviewResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getPopularTop10Product();

    List<ProductResponseDto> getTop10Product(String name);

    List<ProductResponseDto> getTop10Product(String name, String brand);

    ProductResponseDto saveProductDto(ProductDto productDto);

    ProductResponseDto getProduct(String name);

    List<QuestionResponseDto> getQuestion(Long id);

    List<ReviewResponseDto> getReview(Long id);

//    ProductResponseDto getProduct(Long number);
//
//
//    void deleteProduct(Long number) throws Exception;

}
