package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.EventQuestion;
import com.springboot.gguda.data.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventQuestionRepository extends JpaRepository<EventQuestion, Long> {

    List<EventQuestion> findAllByEventProductIdOrderByCreatedAtDesc(Long id); // id로 찾아야하니까.... Product_id를 어떻게 표현하지?

    List<EventQuestion> findAllByMemberIdOrderByCreatedAtDesc(Long id);

    List<EventQuestion> findAllByOrderByCreatedAtDesc();
}
