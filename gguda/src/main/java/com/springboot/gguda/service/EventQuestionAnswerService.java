package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.EventQuestionAnswerDto;
import com.springboot.gguda.data.dto.EventQuestionAnswerResponseDto;
import com.springboot.gguda.data.dto.QuestionAnswerDto;
import com.springboot.gguda.data.dto.QuestionAnswerResponseDto;

import java.util.List;

public interface EventQuestionAnswerService {

    EventQuestionAnswerResponseDto saveEventQuestionAnswerDto(EventQuestionAnswerDto eventQuestionAnswerDto);


    List<EventQuestionAnswerResponseDto> getAllEventQuestionAnswer();

    EventQuestionAnswerResponseDto putEventQuestionAnswer(EventQuestionAnswerDto eventQuestionAnswerDto, Long id);

    void deleteEventQuestionAnswer(Long id);
}
