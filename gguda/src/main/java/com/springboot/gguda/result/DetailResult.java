package com.springboot.gguda.result;

import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.data.dto.QuestionResponseDto;
import lombok.Data;

import java.util.List;

@Data // Getter, Setter, ToString, EqualsAndHashCode
public class DetailResult<T> {
    private ProductResponseDto product;
    private List<QuestionResponseDto> questionList;

    public DetailResult(ProductResponseDto productResponseDto, List<QuestionResponseDto> questionResponseDtoList) {
        this.product = productResponseDto;
        this.questionList = questionResponseDtoList;
    }
}