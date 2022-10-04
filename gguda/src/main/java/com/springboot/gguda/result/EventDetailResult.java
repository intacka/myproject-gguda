package com.springboot.gguda.result;

import com.springboot.gguda.data.dto.*;
import lombok.Data;

import java.util.List;

@Data // Getter, Setter, ToString, EqualsAndHashCode
public class EventDetailResult<T> {
    private EventProductResponseDto eventProduct;
    private List<EventQuestionResponseDto> eventQuestionList;
    private List<EventReviewResponseDto> eventReviewList;

    public EventDetailResult(EventProductResponseDto eventProductResponseDto,
                             List<EventQuestionResponseDto> eventQuestionResponseDtoList,
                             List<EventReviewResponseDto> eventReviewResponseDtoList) {
        this.eventProduct = eventProductResponseDto;
        this.eventQuestionList = eventQuestionResponseDtoList;
        this.eventReviewList = eventReviewResponseDtoList;
    }
}