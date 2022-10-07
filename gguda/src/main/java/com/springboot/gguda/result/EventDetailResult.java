package com.springboot.gguda.result;

import com.springboot.gguda.data.dto.*;
import lombok.Data;

import java.util.List;

@Data // Getter, Setter, ToString, EqualsAndHashCode
public class EventDetailResult<T> {
    private EventProductResponseDto eventProduct;
    private List<EventQuestionResponseDto> eventQuestionList;
    private List<EventQuestionAnswerResponseDto> eventQuestionAnswerList;
    private List<EventReviewResponseDto> eventReviewList;

    public EventDetailResult(EventProductResponseDto eventProductResponseDto,
                             List<EventQuestionResponseDto> eventQuestionResponseDtoList,
                             List<EventQuestionAnswerResponseDto> eventQuestionAnswerResponseDtoList,
                             List<EventReviewResponseDto> eventReviewResponseDtoList) {
        this.eventProduct = eventProductResponseDto;
        this.eventQuestionList = eventQuestionResponseDtoList;
        this.eventQuestionAnswerList = eventQuestionAnswerResponseDtoList;
        this.eventReviewList = eventReviewResponseDtoList;
    }
}