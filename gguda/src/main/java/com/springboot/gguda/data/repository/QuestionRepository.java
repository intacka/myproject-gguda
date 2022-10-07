package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.Question;
import com.springboot.gguda.data.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByProductIdOrderByCreatedAtDesc(Long id); // id로 찾아야하니까.... Product_id를 어떻게 표현하지?

    List<Question> findAllByMemberIdOrderByCreatedAtDesc(Long id);

    List<Question> findAllByOrderByCreatedAtDesc();
}
