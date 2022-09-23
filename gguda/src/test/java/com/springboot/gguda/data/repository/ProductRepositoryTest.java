package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void findTop10ByOrderBySalesDesc() {
//        List<Product> all = productRepository.findTop10OrderBySalesDesc();
//        // 판매순으로 가져오기
//        System.out.println(all);
////        List<Product> samsungNotebook10 = productRepository.findTop10ByNameAndBrandAndOrderBySalesDesc("모니터","벤큐");
//


    }
}