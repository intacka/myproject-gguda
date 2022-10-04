package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.ProductDto;
import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.data.dto.QuestionResponseDto;
import com.springboot.gguda.data.dto.ReviewResponseDto;
import com.springboot.gguda.data.entity.Product;
import com.springboot.gguda.data.entity.Question;
import com.springboot.gguda.data.entity.Review;
import com.springboot.gguda.data.repository.ProductRepository;
import com.springboot.gguda.data.repository.QuestionRepository;
import com.springboot.gguda.data.repository.ReviewRepository;
import com.springboot.gguda.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final QuestionRepository questionRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              QuestionRepository questionRepository,
                              ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.questionRepository = questionRepository;
        this.reviewRepository = reviewRepository;
    }



    @Override
    public List<ProductResponseDto> getPopularTop10Product() {
        List<Product> popularTop10 = productRepository.findTop10ByOrderBySalesDesc();
                // 판매순으로 10개 가져오기

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>(); // 2

        for(Product product : popularTop10){ // 3
            ProductResponseDto dto = ProductResponseDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .createdAt(product.getCreatedAt())
                    .updatedAt(product.getUpdatedAt())
                    .sales(product.getSales())
                    .brand(product.getBrand())
                    .salesType(product.getSalesType())
                    .stock(product.getStock())
                    .build();

            productResponseDtoList.add(dto);
        }

        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> getTop10Product(String name) {
        List<Product> Top10 = productRepository.findTop10ByNameContainingOrderBySalesDesc(name);
        // name에 해당되는 상품 10개 가져오기

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>(); // 2

        for(Product product : Top10){ // 3
            ProductResponseDto dto = ProductResponseDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .createdAt(product.getCreatedAt())
                    .updatedAt(product.getUpdatedAt())
                    .price(product.getPrice())
                    .sales(product.getSales())
                    .brand(product.getBrand())
                    .salesType(product.getSalesType())
                    .stock(product.getStock())
                    .build();

            productResponseDtoList.add(dto);
        }

        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> getTop10Product(String name, String brand) {
        List<Product> Top10 = productRepository.findTop10ByNameContainingAndBrandOrderBySalesDesc(name, brand);
        // name과 brand에 해당하는

        List<ProductResponseDto>productResponseDtoList = new ArrayList<>();

        for(Product product : Top10){ // 3
            ProductResponseDto dto = ProductResponseDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .createdAt(product.getCreatedAt())
                    .updatedAt(product.getUpdatedAt())
                    .price(product.getPrice())
                    .sales(product.getSales())
                    .brand(product.getBrand())
                    .salesType(product.getSalesType())
                    .stock(product.getStock())
                    .build();

            productResponseDtoList.add(dto);
        }

        return productResponseDtoList;
    }






    @Override
    public ProductResponseDto saveProductDto(ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        product.setSales(productDto.getSales());
        product.setBrand(productDto.getBrand());
        product.setStock(productDto.getStock());
        product.setSalesType(productDto.getSalesType());

        productRepository.save(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setCreatedAt(product.getCreatedAt());
        productResponseDto.setUpdatedAt(product.getUpdatedAt());
        productResponseDto.setName(product.getName());
        productResponseDto.setSales(product.getSales());
        productResponseDto.setBrand(product.getBrand());
        productResponseDto.setStock(product.getStock());
        productResponseDto.setSalesType(product.getSalesType());
        productResponseDto.setId(product.getId());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto getProduct(Long id) {
        Product product = productRepository.getById(id);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setCreatedAt(product.getCreatedAt());
        productResponseDto.setUpdatedAt(product.getUpdatedAt());
        productResponseDto.setName(product.getName());
        productResponseDto.setSales(product.getSales());
        productResponseDto.setBrand(product.getBrand());
        productResponseDto.setStock(product.getStock());
        productResponseDto.setSalesType(product.getSalesType());


        return productResponseDto;
    }

    @Override
    public List<QuestionResponseDto> getQuestion(Long id) {

        // 아이디에해당하는 질문리스트받기

        List<Question> questions = questionRepository.findAllByProductIdOrderByCreatedAtDesc(id);

        List<QuestionResponseDto> questionResponseDtoList = new ArrayList<>();

        for(Question question : questions){
            QuestionResponseDto dto = QuestionResponseDto.builder()
                    .id(question.getId())
                    .title(question.getTitle())
                    .content(question.getContent())
                    .privateWhether(question.getPrivateWhether())
                    .createdAt(question.getCreatedAt())
                    .updatedAt(question.getUpdatedAt())
                    .productId(question.getProduct().getId())
                    .memberId(question.getMember().getId())
                    .build();

            questionResponseDtoList.add(dto);
        }

        return questionResponseDtoList;
    }

    @Override
    public List<ReviewResponseDto> getReview(Long id) {
        List<Review> reviews = reviewRepository.findAllByProductIdOrderByCreatedAtDesc(id);

        List<ReviewResponseDto> reviewResponseDtoList = new ArrayList<>();

        for(Review review : reviews){
            ReviewResponseDto dto = ReviewResponseDto.builder()
                    .id(review.getId())
                    .content(review.getContent())
                    .stars(review.getStars())
                    .createdAt(review.getCreatedAt())
                    .updatedAt(review.getUpdatedAt())
                    .productId(review.getProduct().getId())
                    .memberId(review.getMember().getId())
                    .build();

            reviewResponseDtoList.add(dto);
        }

        return reviewResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> getAllProduct() {
        List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.DESC, "brand"));

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        for(Product product : products){ // 3
            ProductResponseDto dto = ProductResponseDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .createdAt(product.getCreatedAt())
                    .updatedAt(product.getUpdatedAt())
                    .price(product.getPrice())
                    .sales(product.getSales())
                    .brand(product.getBrand())
                    .salesType(product.getSalesType())
                    .stock(product.getStock())
                    .build();

            productResponseDtoList.add(dto);
        }

        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> getZeroRental(int value) {
        List<Product> products = productRepository.findAllByPriceIsLessThanEqualOrderBySalesDesc(value);

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        for(Product product : products) {
            ProductResponseDto dto = ProductResponseDto.builder()
                    .id(product.getId())
                    .price(product.getPrice())
                    .createdAt(product.getCreatedAt())
                    .updatedAt(product.getUpdatedAt())
                    .name(product.getName())
                    .sales(product.getSales())
                    .brand(product.getBrand())
                    .stock(product.getStock())
                    .salesType(product.getSalesType())
                    .build();

            productResponseDtoList.add(dto);
        }

        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> getProductListByBrand(String brand) {
        List<Product> products = productRepository.findAllByBrand(brand);

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        for(Product product : products) {
            ProductResponseDto dto = ProductResponseDto.builder()
                    .id(product.getId())
                    .price(product.getPrice())
                    .createdAt(product.getCreatedAt())
                    .updatedAt(product.getUpdatedAt())
                    .name(product.getName())
                    .sales(product.getSales())
                    .brand(product.getBrand())
                    .stock(product.getStock())
                    .salesType(product.getSalesType())
                    .build();

            productResponseDtoList.add(dto);
        }

        return productResponseDtoList;
    }


}
