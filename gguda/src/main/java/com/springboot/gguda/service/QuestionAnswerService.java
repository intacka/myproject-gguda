package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.QuestionAnswerDto;
import com.springboot.gguda.data.dto.QuestionAnswerResponseDto;
import com.springboot.gguda.data.dto.QuestionDto;
import com.springboot.gguda.data.dto.QuestionResponseDto;

import java.util.List;

public interface QuestionAnswerService {

    QuestionAnswerResponseDto saveQuestionAnswerDto(QuestionAnswerDto questionAnswerDto);


    List<QuestionAnswerResponseDto> getAllQuestionAnswer();

    QuestionAnswerResponseDto putQuestionAnswer(QuestionAnswerDto questionAnswerDto, Long id);

    void deleteQuestionAnswer(Long id);
}
