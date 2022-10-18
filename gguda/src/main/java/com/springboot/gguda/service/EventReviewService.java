package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.EventReviewDto;
import com.springboot.gguda.data.dto.EventReviewResponseDto;
import com.springboot.gguda.data.dto.ReviewDto;
import com.springboot.gguda.data.dto.ReviewResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EventReviewService {

    EventReviewResponseDto saveEventReviewDto(EventReviewDto eventReviewDto, List<MultipartFile> files) throws IOException;


    List<EventReviewResponseDto> getEventReviewPage(String content, int page);

    List<EventReviewResponseDto> getEventReviewList(Long id);

    EventReviewResponseDto putEventReview(EventReviewDto eventReviewDto, Long id);

    void deleteEventReview(Long id);

    List<EventReviewResponseDto> getAllEventReview();

    EventReviewResponseDto getEventReview(Long id);
}
