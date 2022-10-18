package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<ProductResponseDto> getPopularTop10Product();

    List<ProductResponseDto> getTop10Product(String name);

    List<ProductResponseDto> getTop10Product(String name, String brand);

    ProductResponseDto saveProductDto(ProductDto productDto, MultipartFile file) throws IOException;

    ProductResponseDto getProduct(Long id);

    List<QuestionResponseDto> getQuestion(Long id);

    List<QuestionAnswerResponseDto> getQuestionAnswer(Long id);

    List<ReviewResponseDto> getReview(Long id);

    List<ProductResponseDto> getAllProduct();

    List<ProductResponseDto> getZeroRental(int value);

    List<ProductResponseDto> getProductListByBrand(String brand);


    ProductResponseDto putProduct(ProductDto productDto, Long id);

    ProductResponseDto deleteProduct(Long id);

    void putStock(Long productId, Integer amount);

    void putState(Long productId);
}
