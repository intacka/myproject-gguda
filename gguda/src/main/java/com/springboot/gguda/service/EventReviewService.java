package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.EventReviewDto;
import com.springboot.gguda.data.dto.EventReviewResponseDto;
import com.springboot.gguda.data.dto.ReviewDto;
import com.springboot.gguda.data.dto.ReviewResponseDto;

import java.util.List;

public interface EventReviewService {

    EventReviewResponseDto saveEventReviewDto(EventReviewDto eventReviewDto);


    List<EventReviewResponseDto> getEventReviewPage(String content, int page);

    List<EventReviewResponseDto> getEventReviewList(Long id);
}
