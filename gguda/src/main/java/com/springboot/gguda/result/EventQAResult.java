package com.springboot.gguda.result;

import com.springboot.gguda.data.dto.EventQuestionAnswerResponseDto;
import com.springboot.gguda.data.dto.EventQuestionResponseDto;
import com.springboot.gguda.data.dto.QuestionAnswerResponseDto;
import com.springboot.gguda.data.dto.QuestionResponseDto;
import lombok.Data;

import java.util.List;

@Data // Getter, Setter, ToString, EqualsAndHashCode
public class EventQAResult<T> {
    private List<EventQuestionResponseDto> eventQuestionList;
    private List<EventQuestionAnswerResponseDto> eventQuestionAnswerList;

    public EventQAResult(List<EventQuestionResponseDto> eventQuestionResponseDtoList,
                         List<EventQuestionAnswerResponseDto> eventQuestionAnswerResponseDtoList) {
        this.eventQuestionList = eventQuestionResponseDtoList;
        this.eventQuestionAnswerList = eventQuestionAnswerResponseDtoList;
    }
}