package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.*;
import com.springboot.gguda.data.entity.*;
import com.springboot.gguda.data.repository.*;
import com.springboot.gguda.service.EventProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventProductServiceImpl implements EventProductService {

    private final EventProductRepository eventProductRepository;
    private final EventQuestionRepository eventQuestionRepository;
    private final EventReviewRepository eventReviewRepository;

    @Autowired
    public EventProductServiceImpl(EventProductRepository eventProductRepository,
                                   EventQuestionRepository eventQuestionRepository,
                                   EventReviewRepository eventReviewRepository) {
        this.eventProductRepository = eventProductRepository;
        this.eventQuestionRepository = eventQuestionRepository;
        this.eventReviewRepository = eventReviewRepository;
    }



    @Override
    public List<EventProductResponseDto> getEventProductPageAll(int page) {
        Page<EventProduct> eventProductPage = eventProductRepository.findAllBy
                (PageRequest.of(page-1,20, Sort.by(Sort.Direction.DESC, "CreatedAt")));
        // 생성순 DESC로 정렬되어 페이징처리    // pageable의 구현체 = PageRequest

        List<EventProduct> eventProducts = eventProductPage.getContent();
        List<EventProductResponseDto> eventProductResponseDtoList = new ArrayList<>();

        for(EventProduct eventProduct : eventProducts) {
            EventProductResponseDto dto = EventProductResponseDto.builder()
                    .id(eventProduct.getId())
                    .name(eventProduct.getName())
                    .sort(eventProduct.getSort())
                    .salesType(eventProduct.getSalesType())
                    .createdAt(eventProduct.getCreatedAt())
                    .updatedAt(eventProduct.getUpdatedAt())
                    .build();

            eventProductResponseDtoList.add(dto);
        }
        return eventProductResponseDtoList;
    }

    @Override
    public List<EventProductResponseDto> getEventProductPage(String sort, int page) {
        Page<EventProduct> eventProductPage = eventProductRepository.findAllBySort
                (sort, PageRequest.of(page-1,20, Sort.by(Sort.Direction.DESC, "CreatedAt")));
        // 생성순 DESC로 정렬되어 페이징처리    // pageable의 구현체 = PageRequest

        List<EventProduct> eventProducts = eventProductPage.getContent();
        List<EventProductResponseDto> eventProductResponseDtoList = new ArrayList<>();

        for(EventProduct eventProduct : eventProducts) {
            EventProductResponseDto dto = EventProductResponseDto.builder()
                    .id(eventProduct.getId())
                    .name(eventProduct.getName())
                    .sort(eventProduct.getSort())
                    .salesType(eventProduct.getSalesType())
                    .createdAt(eventProduct.getCreatedAt())
                    .updatedAt(eventProduct.getUpdatedAt())
                    .build();

            eventProductResponseDtoList.add(dto);
        }
        return eventProductResponseDtoList;
    }



    @Override
    public EventProductResponseDto saveEventProductDto(EventProductDto eventProductDto) {
        EventProduct eventProduct = new EventProduct();
        eventProduct.setName(eventProductDto.getName());
        eventProduct.setSort(eventProductDto.getSort());
        eventProduct.setSalesType(eventProductDto.getSalesType());

        eventProductRepository.save(eventProduct);

        EventProductResponseDto eventProductResponseDto = new EventProductResponseDto();
        eventProductResponseDto.setId(eventProduct.getId());
        eventProductResponseDto.setSort(eventProduct.getSort());
        eventProductResponseDto.setName(eventProduct.getName());
        eventProductResponseDto.setSalesType(eventProduct.getSalesType());
        eventProductResponseDto.setCreatedAt(eventProduct.getCreatedAt());
        eventProductResponseDto.setUpdatedAt(eventProduct.getUpdatedAt());


        return eventProductResponseDto;
    }

    @Override
    public EventProductResponseDto getEventProduct(Long id) {
        EventProduct eventProduct = eventProductRepository.getById(id);

        EventProductResponseDto eventProductResponseDto = new EventProductResponseDto();
        eventProductResponseDto.setId(eventProduct.getId());
        eventProductResponseDto.setCreatedAt(eventProduct.getCreatedAt());
        eventProductResponseDto.setUpdatedAt(eventProduct.getUpdatedAt());
        eventProductResponseDto.setSort(eventProduct.getSort());
        eventProductResponseDto.setName(eventProduct.getName());
        eventProductResponseDto.setSalesType(eventProduct.getSalesType());

        return eventProductResponseDto;
    }

    @Override
    public List<EventQuestionResponseDto> getEventQuestion(Long id) {

        // 아이디에해당하는 질문리스트받기

        List<EventQuestion> eventQuestions = eventQuestionRepository.findAllByEventProductIdOrderByCreatedAtDesc(id); //////////

        List<EventQuestionResponseDto> eventQuestionResponseDtoList = new ArrayList<>();

        for(EventQuestion eventQuestion : eventQuestions){
            EventQuestionResponseDto dto = EventQuestionResponseDto.builder()
                    .id(eventQuestion.getId())
                    .title(eventQuestion.getTitle())
                    .content(eventQuestion.getContent())
                    .privateWhether(eventQuestion.getPrivateWhether())
                    .createdAt(eventQuestion.getCreatedAt())
                    .updatedAt(eventQuestion.getUpdatedAt())
                    .eventProductId(eventQuestion.getEventProduct().getId())
                    .memberId(eventQuestion.getMember().getId())
                    .build();

            eventQuestionResponseDtoList.add(dto);
        }

        return eventQuestionResponseDtoList;
    }

    @Override
    public List<EventReviewResponseDto> getEventReview(Long id) {
        List<EventReview> eventReviews = eventReviewRepository.findAllByEventProductIdOrderByCreatedAtDesc(id);

        List<EventReviewResponseDto> eventReviewResponseDtoList = new ArrayList<>();

        for(EventReview eventReview : eventReviews){
            EventReviewResponseDto dto = EventReviewResponseDto.builder()
                    .id(eventReview.getId())
                    .content(eventReview.getContent())
                    .stars(eventReview.getStars())
                    .createdAt(eventReview.getCreatedAt())
                    .updatedAt(eventReview.getUpdatedAt())
                    .eventProductId(eventReview.getEventProduct().getId())
                    .memberId(eventReview.getMember().getId())
                    .build();

            eventReviewResponseDtoList.add(dto);
        }

        return eventReviewResponseDtoList;
    }


}
