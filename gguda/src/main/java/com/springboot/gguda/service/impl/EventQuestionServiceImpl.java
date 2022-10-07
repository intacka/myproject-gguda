package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.EventQuestionDto;
import com.springboot.gguda.data.dto.EventQuestionResponseDto;
import com.springboot.gguda.data.dto.QuestionResponseDto;
import com.springboot.gguda.data.entity.EventQuestion;
import com.springboot.gguda.data.entity.Question;
import com.springboot.gguda.data.repository.EventProductRepository;
import com.springboot.gguda.data.repository.EventQuestionRepository;
import com.springboot.gguda.data.repository.MemberRepository;
import com.springboot.gguda.service.EventQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventQuestionServiceImpl implements EventQuestionService {

    private final EventQuestionRepository eventQuestionRepository;
    private final EventProductRepository eventProductRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public EventQuestionServiceImpl(EventQuestionRepository eventQuestionRepository, EventProductRepository eventProductRepository, MemberRepository memberRepository) {
        this.eventQuestionRepository = eventQuestionRepository;
        this.eventProductRepository = eventProductRepository;
        this.memberRepository = memberRepository;
    }


    @Override
    public EventQuestionResponseDto saveEventQuestionDto(EventQuestionDto eventQuestionDto) {
        EventQuestion eventQuestion = new EventQuestion();
        eventQuestion.setTitle(eventQuestionDto.getTitle());
        eventQuestion.setContent(eventQuestionDto.getContent());
        eventQuestion.setPrivateWhether(eventQuestionDto.getPrivateWhether());
        eventQuestion.setEventProduct(eventProductRepository.findById(eventQuestionDto.getEventProductId()).get());
        eventQuestion.setMember(memberRepository.findById(eventQuestionDto.getMemberId()).get());

        eventQuestionRepository.save(eventQuestion);

        EventQuestionResponseDto eventQuestionResponseDto = new EventQuestionResponseDto();
        eventQuestionResponseDto.setContent(eventQuestion.getContent());
        eventQuestionResponseDto.setTitle(eventQuestion.getTitle());
        eventQuestionResponseDto.setPrivateWhether(eventQuestion.getPrivateWhether());
        eventQuestionResponseDto.setId(eventQuestion.getId());
        eventQuestionResponseDto.setCreatedAt(eventQuestion.getCreatedAt());
        eventQuestionResponseDto.setUpdatedAt(eventQuestion.getUpdatedAt());
        eventQuestionResponseDto.setEventProductId(eventQuestion.getEventProduct().getId());
        eventQuestionResponseDto.setMemberId(eventQuestion.getMember().getId());

        return eventQuestionResponseDto;
    }

    @Override
    public List<EventQuestionResponseDto> getEventQuestionList(Long id) {
        List<EventQuestion> eventQuestions = eventQuestionRepository.findAllByMemberIdOrderByCreatedAtDesc(id);

        List<EventQuestionResponseDto> eventQuestionResponseDtoList = new ArrayList<>();

        for(EventQuestion eventQuestion : eventQuestions){
            EventQuestionResponseDto dto = EventQuestionResponseDto.builder()
                    .id(eventQuestion.getId())
                    .title(eventQuestion.getTitle())
                    .content(eventQuestion.getContent())
                    .privateWhether(eventQuestion.getPrivateWhether())
                    .eventProductId(eventQuestion.getEventProduct().getId())
                    .memberId(eventQuestion.getMember().getId())
                    .createdAt(eventQuestion.getCreatedAt())
                    .updatedAt(eventQuestion.getUpdatedAt())
                    .build();

            eventQuestionResponseDtoList.add(dto);
        }

        return eventQuestionResponseDtoList;
    }

    @Override
    public List<EventQuestionResponseDto> getAllEventQuestion() {
        List<EventQuestion> eventQuestions = eventQuestionRepository.findAllByOrderByCreatedAtDesc();

        List<EventQuestionResponseDto> eventQuestionResponseDtoList = new ArrayList<>();

        for(EventQuestion eventQuestion : eventQuestions){
            EventQuestionResponseDto dto = EventQuestionResponseDto.builder()
                    .id(eventQuestion.getId())
                    .title(eventQuestion.getTitle())
                    .content(eventQuestion.getContent())
                    .privateWhether(eventQuestion.getPrivateWhether())
                    .createdAt(eventQuestion.getCreatedAt())
                    .updatedAt(eventQuestion.getUpdatedAt())
                    .eventProductId(eventQuestion.getEventProduct().getId())
                    .memberId(eventQuestion.getMember().getId())
                    .build();

            eventQuestionResponseDtoList.add(dto);
        }

        return eventQuestionResponseDtoList;
    }
}
