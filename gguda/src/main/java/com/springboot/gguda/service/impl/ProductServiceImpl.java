package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.ProductDto;
import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.data.dto.QuestionResponseDto;
import com.springboot.gguda.data.entity.Product;
import com.springboot.gguda.data.entity.Question;
import com.springboot.gguda.data.repository.ProductRepository;
import com.springboot.gguda.data.repository.QuestionRepository;
import com.springboot.gguda.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, QuestionRepository questionRepository) {
        this.productRepository = productRepository;
        this.questionRepository = questionRepository;
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
                    .productType(product.getProductType())
                    .price(product.getPrice())
                    .regDate(product.getRegDate())
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
                    .productType(product.getProductType())
                    .price(product.getPrice())
                    .regDate(product.getRegDate())
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
                    .productType(product.getProductType())
                    .price(product.getPrice())
                    .regDate(product.getRegDate())
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
        product.setProductType(productDto.getProductType());
        product.setName(productDto.getName());
        product.setRegDate(productDto.getRegDate());
        product.setSales(productDto.getSales());
        product.setBrand(productDto.getBrand());
        product.setStock(productDto.getStock());
        product.setSalesType(productDto.getSalesType());

        productRepository.save(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setProductType(product.getProductType());
        productResponseDto.setName(product.getName());
        productResponseDto.setRegDate(product.getRegDate());
        productResponseDto.setSales(product.getSales());
        productResponseDto.setBrand(product.getBrand());
        productResponseDto.setStock(product.getStock());
        productResponseDto.setSalesType(product.getSalesType());
        productResponseDto.setId(product.getId());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto getProduct(String name) {
        Product product = productRepository.findByNameContaining(name);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setProductType(product.getProductType());
        productResponseDto.setName(product.getName());
        productResponseDto.setRegDate(product.getRegDate());
        productResponseDto.setSales(product.getSales());
        productResponseDto.setBrand(product.getBrand());
        productResponseDto.setStock(product.getStock());
        productResponseDto.setSalesType(product.getSalesType());


        return productResponseDto;
    }

    @Override
    public List<QuestionResponseDto> getQuestion(Long id) {

        // 아이디에해당하는 질문리스트받기

        List<Question> questions = questionRepository.findAllByProductIdOrderByRegDateDesc(id);

        List<QuestionResponseDto> questionResponseDtoList = new ArrayList<>();

        for(Question question : questions){
            QuestionResponseDto dto = QuestionResponseDto.builder()
                    .id(question.getId())
                    .title(question.getTitle())
                    .content(question.getContent())
                    .privateWhether(question.getPrivateWhether())
                    .regDate(question.getRegDate())
                    .productId(question.getProduct().getId())
                    .memberId(question.getMember().getId())
                    .build();

            questionResponseDtoList.add(dto);
        }

        return questionResponseDtoList;
    }


}
