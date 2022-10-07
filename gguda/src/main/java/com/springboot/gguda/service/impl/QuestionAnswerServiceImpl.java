package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.*;
import com.springboot.gguda.data.entity.Question;
import com.springboot.gguda.data.entity.QuestionAnswer;
import com.springboot.gguda.data.entity.Review;
import com.springboot.gguda.data.repository.MemberRepository;
import com.springboot.gguda.data.repository.ProductRepository;
import com.springboot.gguda.data.repository.QuestionAnswerRepository;
import com.springboot.gguda.data.repository.QuestionRepository;
import com.springboot.gguda.service.QuestionAnswerService;
import com.springboot.gguda.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionAnswerServiceImpl implements QuestionAnswerService {

    private final QuestionRepository questionRepository;
    private final QuestionAnswerRepository questionAnswerRepository;

    @Autowired
    public QuestionAnswerServiceImpl(QuestionRepository questionRepository,
                                     ProductRepository productRepository,
                                     MemberRepository memberRepository,
                                     QuestionAnswerRepository questionAnswerRepository) {
        this.questionRepository = questionRepository;
        this.questionAnswerRepository = questionAnswerRepository;
    }


    @Override
    public QuestionAnswerResponseDto saveQuestionAnswerDto(QuestionAnswerDto questionAnswerDto) {
        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.setContent(questionAnswerDto.getContent());
        questionAnswer.setPrivateWhether(questionAnswerDto.getPrivateWhether());
        questionAnswer.setQuestion(questionRepository.findById(questionAnswerDto.getQuestionId()).get());

        questionAnswerRepository.save(questionAnswer);

        QuestionAnswerResponseDto questionAnswerResponseDto = new QuestionAnswerResponseDto();

        questionAnswerResponseDto.setId(questionAnswer.getId());
        questionAnswerResponseDto.setContent(questionAnswer.getContent());
        questionAnswerResponseDto.setPrivateWhether(questionAnswer.getPrivateWhether());
        questionAnswerResponseDto.setQuestionId(questionAnswer.getQuestion().getId());
        questionAnswerResponseDto.setCreatedAt(questionAnswer.getCreatedAt());
        questionAnswerResponseDto.setUpdatedAt(questionAnswer.getUpdatedAt());

        return questionAnswerResponseDto;
    }

    @Override
    public List<QuestionAnswerResponseDto> getAllQuestionAnswer() {
        List<QuestionAnswer> questionAnswers = questionAnswerRepository.findAllByOrderByCreatedAtDesc();

        List<QuestionAnswerResponseDto> questionAnswerResponseDtoList = new ArrayList<>();

        for(QuestionAnswer questionAnswer : questionAnswers){
            QuestionAnswerResponseDto dto = QuestionAnswerResponseDto.builder()
                    .id(questionAnswer.getId())
                    .content(questionAnswer.getContent())
                    .privateWhether(questionAnswer.getPrivateWhether())
                    .questionId(questionAnswer.getQuestion().getId())
                    .createdAt(questionAnswer.getCreatedAt())
                    .updatedAt(questionAnswer.getUpdatedAt())
                    .build();

            questionAnswerResponseDtoList.add(dto);
        }

        return questionAnswerResponseDtoList;
    }

    @Override
    public QuestionAnswerResponseDto putQuestionAnswer(QuestionAnswerDto questionAnswerDto, Long id) {
        QuestionAnswer questionAnswer = questionAnswerRepository.getById(id);

        questionAnswer.setContent(questionAnswerDto.getContent());
        questionAnswer.setPrivateWhether(questionAnswerDto.getPrivateWhether());
        questionAnswer.setQuestion(questionRepository.getById(questionAnswerDto.getQuestionId()));

        questionAnswerRepository.save(questionAnswer);

        QuestionAnswerResponseDto questionAnswerResponseDto = new QuestionAnswerResponseDto();
        questionAnswerResponseDto.setId(questionAnswer.getId());
        questionAnswerResponseDto.setContent(questionAnswer.getContent());
        questionAnswerResponseDto.setPrivateWhether(questionAnswer.getPrivateWhether());
        questionAnswerResponseDto.setQuestionId(questionAnswer.getQuestion().getId());
        questionAnswerResponseDto.setCreatedAt(questionAnswer.getCreatedAt());
        questionAnswerResponseDto.setUpdatedAt(questionAnswer.getUpdatedAt());

        return questionAnswerResponseDto;
    }

    @Override
    public void deleteQuestionAnswer(Long id) {
        questionAnswerRepository.deleteById(id);
    }

}
