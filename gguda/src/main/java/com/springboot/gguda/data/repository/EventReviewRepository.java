package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.EventReview;
import com.springboot.gguda.data.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventReviewRepository extends JpaRepository<EventReview, Long> {

     List<EventReview> findAllByEventProductIdOrderByCreatedAtDesc(Long id);

     Page<EventReview> findAllByContentContaining(String content, Pageable pageable);

     List<EventReview> findAllByMemberIdOrderByCreatedAtDesc(Long id);

    List<EventReview> findAllByOrderByCreatedAtDesc();
}
