package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.Product;
import com.springboot.gguda.data.entity.Question;
import com.springboot.gguda.data.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

     List<Review> findAllByProductIdOrderByCreatedAtDesc(Long id); // id로 찾아야하니까.... Product_id를 어떻게 표현하지?

     Page<Review> findAllByContentContaining(String content, Pageable pageable);

     List<Review> findAllByMemberIdOrderByCreatedAtDesc(Long id);

     List<Review> findAllByOrderByCreatedAtDesc();
}
