package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.EventQuestionDto;
import com.springboot.gguda.data.dto.EventQuestionResponseDto;
import com.springboot.gguda.data.dto.QuestionDto;
import com.springboot.gguda.data.dto.QuestionResponseDto;

import java.util.List;

public interface EventQuestionService {

    EventQuestionResponseDto saveEventQuestionDto(EventQuestionDto eventQuestionDto);
    List<EventQuestionResponseDto> getEventQuestionList(Long id);

    List<EventQuestionResponseDto> getAllEventQuestion();
}
