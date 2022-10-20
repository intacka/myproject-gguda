package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.*;
import com.springboot.gguda.data.entity.*;
import com.springboot.gguda.data.repository.ProductRepository;
import com.springboot.gguda.data.repository.QuestionAnswerRepository;
import com.springboot.gguda.data.repository.QuestionRepository;
import com.springboot.gguda.data.repository.ReviewRepository;
import com.springboot.gguda.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final QuestionRepository questionRepository;
    private final QuestionAnswerRepository questionAnswerRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              QuestionRepository questionRepository,
                              ReviewRepository reviewRepository,
                              QuestionAnswerRepository questionAnswerRepository) {
        this.productRepository = productRepository;
        this.questionRepository = questionRepository;
        this.reviewRepository = reviewRepository;
        this.questionAnswerRepository = questionAnswerRepository;
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
                    .filepath(product.getFilepath())
                    .filename(product.getFilename())
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
                    .filepath(product.getFilepath())
                    .filename(product.getFilename())
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
                    .filepath(product.getFilepath())
                    .filename(product.getFilename())
                    .build();

            productResponseDtoList.add(dto);
        }

        return productResponseDtoList;
    }






    @Override
    public ProductResponseDto saveProductDto(ProductDto productDto, MultipartFile file) throws IOException {

        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        product.setSales(productDto.getSales());
        product.setBrand(productDto.getBrand());
        product.setStock(productDto.getStock());
        product.setSalesType(productDto.getSalesType());

        if (file==null) {
            product.setFilename(null);
            product.setFilepath(null);
        } else {

                // 파일 저장
                String projectPath = System.getProperty("user.dir") + "\\gguda\\src\\main\\resources\\static\\files";
                UUID uuid = UUID.randomUUID();
                String fileName = uuid + "_" + file.getOriginalFilename();
                File saveFile = new File(projectPath, fileName);
                file.transferTo(saveFile);

            product.setFilename(fileName);
            product.setFilepath("/files/" + fileName);

        }


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
        productResponseDto.setFilepath(product.getFilepath());
        productResponseDto.setFilename(product.getFilename());

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
        productResponseDto.setFilename(product.getFilename());
        productResponseDto.setFilepath(product.getFilepath());


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
    public List<QuestionAnswerResponseDto> getQuestionAnswer(Long id) { // id는 productId이다
        // 아이디에해당하는 질문답변리스트받기
        List<Question> questions = questionRepository.findAllByProductIdOrderByCreatedAtDesc(id);

        List<QuestionAnswerResponseDto> questionAnswerResponseDtoList = new ArrayList<>();

        for(Question question : questions){

            Long questionIdTemp = question.getId();


            QuestionAnswer questionAnswer = questionAnswerRepository.findByQuestionId(questionIdTemp);
            if (questionAnswer!=null) {
                QuestionAnswerResponseDto dto = QuestionAnswerResponseDto.builder()
                        .id(questionAnswer.getId())
                        .content(questionAnswer.getContent())
                        .privateWhether(questionAnswer.getPrivateWhether())
                        .questionId(questionAnswer.getQuestion().getId())
                        .createdAt(questionAnswer.getCreatedAt())
                        .updatedAt(questionAnswer.getUpdatedAt())
                        .build();

                questionAnswerResponseDtoList.add(dto);

            }

        }

        return questionAnswerResponseDtoList;
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
                    .filename(review.getFilename())
                    .filepath(review.getFilepath())
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
                    .filename(product.getFilename())
                    .filepath(product.getFilepath())
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
                    .filename(product.getFilename())
                    .filepath(product.getFilepath())
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
                    .filename(product.getFilename())
                    .filepath(product.getFilepath())
                    .build();

            productResponseDtoList.add(dto);
        }

        return productResponseDtoList;
    }

    @Override
    public ProductResponseDto putProduct(ProductDto productDto, Long id) {
        Product product = productRepository.getById(id);

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
        productResponseDto.setFilepath(product.getFilepath());
        productResponseDto.setFilename(product.getFilename());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.get();

        product.setBrand(null);
        product.setStock(null);
        product.setName(null);
        product.setPrice(null);
        product.setSales(null);
        product.setSalesType(null);

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
        productResponseDto.setFilename(product.getFilename());
        productResponseDto.setFilepath(product.getFilepath());

        return productResponseDto;
    }

    @Override
    public void putStock(Long productId, Integer amount) {
        Product thisProduct = productRepository.getById(productId);
        Integer originalStock = thisProduct.getStock();
        thisProduct.setStock(originalStock - amount);   // 재고량변동
        thisProduct.setSales(thisProduct.getSales() + amount); // 판매량 증가

        if (thisProduct.getStock()==0) {
            thisProduct.setSalesType(1);                    //만약 다 팔리면 품절상태로 전환
        }
    }

    @Override
    public void putState(Long productId) {
        Product thisProduct = productRepository.getById(productId);
        Integer originalStock = thisProduct.getStock();
        thisProduct.setStock(originalStock - 1);   // 재고량변동
        thisProduct.setSales(thisProduct.getSales() + 1); // 판매량 증가

        if (thisProduct.getStock()==0) {
            thisProduct.setSalesType(1);                    //만약 다 팔리면 품절상태로 전환
        }
    }


}
