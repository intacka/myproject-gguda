package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.EventReviewDto;
import com.springboot.gguda.data.dto.EventReviewResponseDto;
import com.springboot.gguda.data.dto.ReviewDto;
import com.springboot.gguda.data.dto.ReviewResponseDto;
import com.springboot.gguda.data.entity.EventReview;
import com.springboot.gguda.data.entity.Review;
import com.springboot.gguda.data.repository.*;
import com.springboot.gguda.service.EventReviewService;
import com.springboot.gguda.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventReviewServiceImpl implements EventReviewService {

    private final EventReviewRepository eventReviewRepository;
    private final EventProductRepository eventProductRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public EventReviewServiceImpl(EventReviewRepository eventReviewRepository, EventProductRepository eventProductRepository, MemberRepository memberRepository) {
        this.eventReviewRepository = eventReviewRepository;
        this.eventProductRepository = eventProductRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public EventReviewResponseDto saveEventReviewDto(EventReviewDto eventReviewDto) {
        EventReview eventReview = new EventReview();
        eventReview.setContent(eventReviewDto.getContent());
        eventReview.setStars(eventReviewDto.getStars());
        eventReview.setEventProduct(eventProductRepository.findById(eventReviewDto.getEventProductId()).get());
        eventReview.setMember(memberRepository.findById(eventReviewDto.getMemberId()).get());

        eventReviewRepository.save(eventReview);

        EventReviewResponseDto eventReviewResponseDto = new EventReviewResponseDto();
        eventReviewResponseDto.setId(eventReview.getId());
        eventReviewResponseDto.setContent(eventReview.getContent());
        eventReviewResponseDto.setStars(eventReview.getStars());
        eventReviewResponseDto.setCreatedAt(eventReview.getCreatedAt());
        eventReviewResponseDto.setUpdatedAt(eventReview.getUpdatedAt());
        eventReviewResponseDto.setEventProductId(eventReview.getEventProduct().getId());
        eventReviewResponseDto.setMemberId(eventReview.getMember().getId());

        return eventReviewResponseDto;
    }

    @Override
    public List<EventReviewResponseDto> getEventReviewPage(String content, int page) {
        Page<EventReview> eventReviewPage = eventReviewRepository.findAllByContentContaining(content, PageRequest.of(page-1,12, Sort.by(Sort.Direction.DESC, "CreatedAt")));
        // 생성순 DESC로 정렬되어 페이징처리    // pageable의 구현체 = PageRequest

        List<EventReview> eventReviews = eventReviewPage.getContent();
        List<EventReviewResponseDto> eventReviewResponseDtoList = new ArrayList<>();

        for(EventReview eventReview : eventReviews) {
            EventReviewResponseDto dto = EventReviewResponseDto.builder()
                    .id(eventReview.getId())
                    .content(eventReview.getContent())
                    .stars(eventReview.getStars())
                    .eventProductId(eventReview.getEventProduct().getId())
                    .memberId(eventReview.getMember().getId())
                    .createdAt(eventReview.getCreatedAt())
                    .updatedAt(eventReview.getUpdatedAt())
                    .build();

            eventReviewResponseDtoList.add(dto);
        }
        return eventReviewResponseDtoList;
    }

    @Override
    public List<EventReviewResponseDto> getEventReviewList(Long id) {

        List<EventReview> eventReviews = eventReviewRepository.findAllByMemberIdOrderByCreatedAtDesc(id);

        List<EventReviewResponseDto> eventReviewResponseDtoList = new ArrayList<>();

        for(EventReview eventReview : eventReviews){
            EventReviewResponseDto dto = EventReviewResponseDto.builder()
                    .id(eventReview.getId())
                    .content(eventReview.getContent())
                    .stars(eventReview.getStars())
                    .eventProductId(eventReview.getEventProduct().getId())
                    .memberId(eventReview.getMember().getId())
                    .createdAt(eventReview.getCreatedAt())
                    .updatedAt(eventReview.getUpdatedAt())
                    .build();

            eventReviewResponseDtoList.add(dto);
        }

        return eventReviewResponseDtoList;
    }


}
