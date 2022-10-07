package com.springboot.gguda.result;

import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.data.dto.QuestionAnswerResponseDto;
import com.springboot.gguda.data.dto.QuestionResponseDto;
import com.springboot.gguda.data.dto.ReviewResponseDto;
import lombok.Data;

import java.util.List;

@Data // Getter, Setter, ToString, EqualsAndHashCode
public class QAResult<T> {
    private List<QuestionResponseDto> questionList;
    private List<QuestionAnswerResponseDto> questionAnswerList;

    public QAResult(List<QuestionResponseDto> questionResponseDtoList,
                    List<QuestionAnswerResponseDto> questionAnswerResponseDtoList) {
        this.questionList = questionResponseDtoList;
        this.questionAnswerList = questionAnswerResponseDtoList;
    }
}