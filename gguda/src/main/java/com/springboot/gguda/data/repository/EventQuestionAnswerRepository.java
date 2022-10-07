package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.EventQuestionAnswer;
import com.springboot.gguda.data.entity.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventQuestionAnswerRepository extends JpaRepository<EventQuestionAnswer, Long> {

    EventQuestionAnswer getByEventQuestionId(Long id);

    List<EventQuestionAnswer> findAllByOrderByCreatedAtDesc();
}
