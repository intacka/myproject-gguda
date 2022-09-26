package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByProductIdOrderByRegDateDesc(Long id); // id로 찾아야하니까.... Product_id를 어떻게 표현하지?


}
