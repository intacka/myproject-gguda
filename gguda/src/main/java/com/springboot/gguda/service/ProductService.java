package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.ProductDto;
import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.data.dto.QuestionResponseDto;
import com.springboot.gguda.data.dto.ReviewResponseDto;
import com.springboot.gguda.data.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getPopularTop10Product();

    List<ProductResponseDto> getTop10Product(String name);

    List<ProductResponseDto> getTop10Product(String name, String brand);

    ProductResponseDto saveProductDto(ProductDto productDto);

    ProductResponseDto getProduct(Long id);

    List<QuestionResponseDto> getQuestion(Long id);

    List<ReviewResponseDto> getReview(Long id);

    List<ProductResponseDto> getAllProduct();

    List<ProductResponseDto> getZeroRental(int value);

    List<ProductResponseDto> getProductListByBrand(String brand);


}
