package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.ProductDto;
import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.data.dto.QuestionDto;
import com.springboot.gguda.data.dto.QuestionResponseDto;
import com.springboot.gguda.data.entity.Product;
import com.springboot.gguda.data.entity.Question;
import com.springboot.gguda.data.repository.MemberRepository;
import com.springboot.gguda.data.repository.ProductRepository;
import com.springboot.gguda.data.repository.QuestionRepository;
import com.springboot.gguda.service.ProductService;
import com.springboot.gguda.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, ProductRepository productRepository, MemberRepository memberRepository) {
        this.questionRepository = questionRepository;
        this.productRepository = productRepository;
        this.memberRepository = memberRepository;
    }


    @Override
    public QuestionResponseDto saveQuestionDto(QuestionDto questionDto) {
        Question question = new Question();
        question.setTitle(questionDto.getTitle());
        question.setContent(questionDto.getContent());
        question.setRegDate(questionDto.getRegDate());
        question.setPrivateWhether(questionDto.getPrivateWhether());
        question.setProduct(productRepository.findById(questionDto.getProductId()).get());
        question.setMember(memberRepository.findById(questionDto.getMemberId()).get());

        questionRepository.save(question);

        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
        questionResponseDto.setContent(question.getContent());
        questionResponseDto.setTitle(question.getTitle());
        questionResponseDto.setPrivateWhether(question.getPrivateWhether());
        questionResponseDto.setId(question.getId());
        questionResponseDto.setRegDate(question.getRegDate());
        questionResponseDto.setProductId(question.getProduct().getId());
        questionResponseDto.setMemberId(question.getMember().getId());

        return questionResponseDto;
    }
}
