package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.EventQuestionAnswerDto;
import com.springboot.gguda.data.dto.EventQuestionAnswerResponseDto;
import com.springboot.gguda.data.dto.QuestionAnswerDto;
import com.springboot.gguda.data.dto.QuestionAnswerResponseDto;
import com.springboot.gguda.data.entity.EventQuestionAnswer;
import com.springboot.gguda.data.entity.QuestionAnswer;
import com.springboot.gguda.data.repository.*;
import com.springboot.gguda.service.EventQuestionAnswerService;
import com.springboot.gguda.service.QuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventQuestionAnswerServiceImpl implements EventQuestionAnswerService {

    private final EventQuestionRepository eventQuestionRepository;
    private final EventQuestionAnswerRepository eventQuestionAnswerRepository;

    @Autowired
    public EventQuestionAnswerServiceImpl(EventQuestionRepository eventQuestionRepository,
                                          ProductRepository productRepository,
                                          MemberRepository memberRepository,
                                          EventQuestionAnswerRepository eventQuestionAnswerRepository) {
        this.eventQuestionRepository = eventQuestionRepository;
        this.eventQuestionAnswerRepository = eventQuestionAnswerRepository;
    }


    @Override
    public EventQuestionAnswerResponseDto saveEventQuestionAnswerDto(EventQuestionAnswerDto eventQuestionAnswerDto) {
        EventQuestionAnswer eventQuestionAnswer = new EventQuestionAnswer();
        eventQuestionAnswer.setContent(eventQuestionAnswerDto.getContent());
        eventQuestionAnswer.setPrivateWhether(eventQuestionAnswerDto.getPrivateWhether());
        eventQuestionAnswer.setEventQuestion(eventQuestionRepository.findById(eventQuestionAnswerDto.getEventQuestionId()).get());

        eventQuestionAnswerRepository.save(eventQuestionAnswer);

        EventQuestionAnswerResponseDto eventQuestionAnswerResponseDto = new EventQuestionAnswerResponseDto();

        eventQuestionAnswerResponseDto.setId(eventQuestionAnswer.getId());
        eventQuestionAnswerResponseDto.setContent(eventQuestionAnswer.getContent());
        eventQuestionAnswerResponseDto.setPrivateWhether(eventQuestionAnswer.getPrivateWhether());
        eventQuestionAnswerResponseDto.setEventQuestionId(eventQuestionAnswer.getEventQuestion().getId());
        eventQuestionAnswerResponseDto.setCreatedAt(eventQuestionAnswer.getCreatedAt());
        eventQuestionAnswerResponseDto.setUpdatedAt(eventQuestionAnswer.getUpdatedAt());

        return eventQuestionAnswerResponseDto;
    }

    @Override
    public List<EventQuestionAnswerResponseDto> getAllEventQuestionAnswer() {
        List<EventQuestionAnswer> eventQuestionAnswers = eventQuestionAnswerRepository.findAllByOrderByCreatedAtDesc();

        List<EventQuestionAnswerResponseDto> eventQuestionAnswerResponseDtoList = new ArrayList<>();

        for(EventQuestionAnswer eventQuestionAnswer : eventQuestionAnswers){
            EventQuestionAnswerResponseDto dto = EventQuestionAnswerResponseDto.builder()
                    .id(eventQuestionAnswer.getId())
                    .content(eventQuestionAnswer.getContent())
                    .privateWhether(eventQuestionAnswer.getPrivateWhether())
                    .eventQuestionId(eventQuestionAnswer.getEventQuestion().getId())
                    .createdAt(eventQuestionAnswer.getCreatedAt())
                    .updatedAt(eventQuestionAnswer.getUpdatedAt())
                    .build();

            eventQuestionAnswerResponseDtoList.add(dto);
        }

        return eventQuestionAnswerResponseDtoList;
    }

    @Override
    public EventQuestionAnswerResponseDto putEventQuestionAnswer(EventQuestionAnswerDto eventQuestionAnswerDto, Long id) {
        EventQuestionAnswer eventQuestionAnswer = eventQuestionAnswerRepository.getById(id);

        eventQuestionAnswer.setContent(eventQuestionAnswerDto.getContent());
        eventQuestionAnswer.setPrivateWhether(eventQuestionAnswerDto.getPrivateWhether());
        eventQuestionAnswer.setEventQuestion(eventQuestionRepository.getById(eventQuestionAnswerDto.getEventQuestionId()));

        eventQuestionAnswerRepository.save(eventQuestionAnswer);

        EventQuestionAnswerResponseDto eventQuestionAnswerResponseDto = new EventQuestionAnswerResponseDto();
        eventQuestionAnswerResponseDto.setId(eventQuestionAnswer.getId());
        eventQuestionAnswerResponseDto.setContent(eventQuestionAnswer.getContent());
        eventQuestionAnswerResponseDto.setPrivateWhether(eventQuestionAnswer.getPrivateWhether());
        eventQuestionAnswerResponseDto.setEventQuestionId(eventQuestionAnswer.getEventQuestion().getId());
        eventQuestionAnswerResponseDto.setCreatedAt(eventQuestionAnswer.getCreatedAt());
        eventQuestionAnswerResponseDto.setUpdatedAt(eventQuestionAnswer.getUpdatedAt());

        return eventQuestionAnswerResponseDto;
    }

    @Override
    public void deleteEventQuestionAnswer(Long id) {
        eventQuestionAnswerRepository.deleteById(id);
    }

}
