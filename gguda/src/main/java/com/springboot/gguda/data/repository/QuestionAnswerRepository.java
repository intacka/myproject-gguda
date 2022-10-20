package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {

    QuestionAnswer getByQuestionId(Long id);

    List<QuestionAnswer> findAllByOrderByCreatedAtDesc();

    QuestionAnswer findByQuestionId(Long questionIdTemp);
}
