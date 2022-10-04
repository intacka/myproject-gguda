package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.Product;
import com.springboot.gguda.data.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findTop10ByOrderBySalesDesc();
    List<Product> findTop10ByNameContainingOrderBySalesDesc(String name);

    List<Product> findTop10ByNameContainingAndBrandOrderBySalesDesc(String name, String brand);

//    Product findByNameContaining(String name);

    List<Product>  findAllByPriceIsLessThanEqualOrderBySalesDesc(int value);

    List<Product> findAll(Sort sort);

    List<Product> findAllByBrand(String brand);


}
