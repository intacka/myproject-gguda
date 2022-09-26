package com.springboot.gguda.result;

import com.springboot.gguda.data.dto.ProductResponseDto;
import com.springboot.gguda.data.dto.QuestionResponseDto;
import com.springboot.gguda.data.dto.ReviewResponseDto;
import lombok.Data;

import java.util.List;

@Data // Getter, Setter, ToString, EqualsAndHashCode
public class DetailResult<T> {
    private ProductResponseDto product;
    private List<QuestionResponseDto> questionList;
    private List<ReviewResponseDto> reviewList;

    public DetailResult(ProductResponseDto productResponseDto,
                        List<QuestionResponseDto> questionResponseDtoList,
                        List<ReviewResponseDto> reviewResponseDtoList) {
        this.product = productResponseDto;
        this.questionList = questionResponseDtoList;
        this.reviewList = reviewResponseDtoList;
    }
}