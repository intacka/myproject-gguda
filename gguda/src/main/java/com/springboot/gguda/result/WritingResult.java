package com.springboot.gguda.result;

import com.springboot.gguda.data.dto.*;
import lombok.Data;

import java.util.List;

@Data // Getter, Setter, ToString, EqualsAndHashCode
public class WritingResult<T> {
    private List<ReviewResponseDto> reviewList;
    private List<EventReviewResponseDto> eventReviewList;
    private List<QuestionResponseDto> questionList;
    private List<EventQuestionResponseDto> eventQuestionList;

    public WritingResult(List<ReviewResponseDto> reviewResponseDtoList,
                         List<EventReviewResponseDto> eventReviewResponseDtoList,
                         List<QuestionResponseDto> questionResponseDtoList,
                         List<EventQuestionResponseDto> eventQuestionResponseDtoList
                         ) {
        this.reviewList = reviewResponseDtoList;
        this.eventReviewList = eventReviewResponseDtoList;
        this.questionList = questionResponseDtoList;
        this.eventQuestionList = eventQuestionResponseDtoList;
    }
}