package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.ProductDto;
import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.data.dto.QuestionDto;
import com.springboot.gguda.data.dto.QuestionResponseDto;

public interface QuestionService {

    QuestionResponseDto saveQuestionDto(QuestionDto questionDto);

}
