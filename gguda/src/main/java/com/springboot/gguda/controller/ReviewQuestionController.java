package com.springboot.gguda.controller;

import com.springboot.gguda.data.dto.*;
import com.springboot.gguda.result.QAResult;
import com.springboot.gguda.result.WritingResult;
import com.springboot.gguda.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewQuestionController {
    private final QuestionService questionService;
    private final QuestionAnswerService questionAnswerService;
    private final ReviewService reviewService;
    private final EventReviewService eventReviewService;
    private final EventQuestionService eventQuestionService;
    @Autowired
    public ReviewQuestionController(QuestionService questionService,
                                    QuestionAnswerService questionAnswerService,
                                    ReviewService reviewService,
                                    EventReviewService eventReviewService,
                                    EventQuestionService eventQuestionService) {
        this.questionService = questionService;
        this.questionAnswerService = questionAnswerService;
        this.reviewService = reviewService;
        this.eventReviewService = eventReviewService;
        this.eventQuestionService = eventQuestionService;
    }

    // 질문등록하기
    @PostMapping(value = "/question/register")
    public ResponseEntity<QuestionResponseDto> createQuestion(@RequestBody QuestionDto questionDto) {
        QuestionResponseDto questionResponseDto = questionService.saveQuestionDto(questionDto);

        return ResponseEntity.status(HttpStatus.OK).body(questionResponseDto);
    }

    // 답변 등록하기
    @PostMapping(value = "/answer/register")
    public ResponseEntity<QuestionAnswerResponseDto> createQuestionAnswer(@RequestBody QuestionAnswerDto questionAnswerDto) {
        QuestionAnswerResponseDto questionAnswerResponseDto = questionAnswerService.saveQuestionAnswerDto(questionAnswerDto);

        return ResponseEntity.status(HttpStatus.OK).body(questionAnswerResponseDto);
    }

    // 답변 수정하기
    @PutMapping(value = "/answer/put")
    public ResponseEntity<QuestionAnswerResponseDto> putQuestionAnswer(@RequestBody QuestionAnswerDto questionAnswerDto, Long id) {
        QuestionAnswerResponseDto questionAnswerResponseDto = questionAnswerService.putQuestionAnswer(questionAnswerDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(questionAnswerResponseDto);
    }

    // 답변 삭제하기
    @DeleteMapping(value = "/answer/delete")
    public boolean dropQuestionAnswer(Long id) {
        questionAnswerService.deleteQuestionAnswer(id);
        return true;
    }

    // 모든질문답변조회 (관리자용)
    @GetMapping(value = "/view/question-answer") // 모든 질문,답변 가져오기(관리자용)
    public QAResult getQA() {
        List<QuestionResponseDto> questionResponseDtos = questionService.getAllQuestion();
        List<QuestionAnswerResponseDto> questionAnswerResponseDtos = questionAnswerService.getAllQuestionAnswer();
        // 모든 질문, 답변 리스트로 쫙 가져오기

        return new QAResult(questionResponseDtos, questionAnswerResponseDtos);
    }

    // 회원id(memberId)로 후기질문들 찾기
    @GetMapping(value = "/view-all/question-answer/bymemberid")
    public WritingResult getWritingList(Long id) {
        List<ReviewResponseDto> reviewResponseDtos = reviewService.getReviewList(id);
        List<EventReviewResponseDto> eventReviewResponseDtos = eventReviewService.getEventReviewList(id);
        List<QuestionResponseDto> questionResponseDtos = questionService.getQuestionList(id);
        List<EventQuestionResponseDto> eventQuestionResponseDtos = eventQuestionService.getEventQuestionList(id);

        return new WritingResult(reviewResponseDtos, eventReviewResponseDtos, questionResponseDtos, eventQuestionResponseDtos);
    }


}
