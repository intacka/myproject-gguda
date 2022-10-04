package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.data.entity.Product;
import com.springboot.gguda.data.entity.Question;
import org.hibernate.annotations.Parameter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    QuestionRepository questionRepository;

    @ParameterizedTest(name = "id")
    @ValueSource(longs = 1L)
    void findAllByIdOrderByRegDateDesc(Long id) {
        List<Question> questions = questionRepository.findAllByProductIdOrderByCreatedAtDesc(1L);
        System.out.println(questions);
////        List<Product> samsungNotebook10 = productRepository.findTop10ByNameAndBrandAndOrderBySalesDesc("모니터","벤큐");
//

    }



}