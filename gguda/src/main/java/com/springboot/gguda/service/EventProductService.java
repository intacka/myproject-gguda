package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EventProductService {

    List<EventProductResponseDto> getEventProductPageAll(int page);

    List<EventProductResponseDto> getEventProductPage(String sort, int page);

    EventProductResponseDto saveEventProductDto(EventProductDto eventProductDto, List<MultipartFile> files) throws IOException;

    EventProductResponseDto getEventProduct(Long id);

    List<EventQuestionResponseDto> getEventQuestion(Long id);

    List<EventReviewResponseDto> getEventReview(Long id);

    EventProductResponseDto putEventProduct(EventProductDto eventProductDto, Long id);

    EventProductResponseDto deleteEventProduct(Long id);

    List<EventQuestionAnswerResponseDto> getEventQuestionAnswer(Long id);
}
