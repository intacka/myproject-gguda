package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.QuestionDto;
import com.springboot.gguda.data.dto.QuestionResponseDto;
import com.springboot.gguda.data.dto.ReviewResponseDto;

import java.util.List;

public interface QuestionService {

    QuestionResponseDto saveQuestionDto(QuestionDto questionDto);

    List<QuestionResponseDto> getQuestionList(Long id);

    List<QuestionResponseDto> getAllQuestion();
}
