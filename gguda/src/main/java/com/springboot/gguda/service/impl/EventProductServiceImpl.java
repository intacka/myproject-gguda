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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventProductServiceImpl implements EventProductService {

    private final EventProductRepository eventProductRepository;
    private final EventReviewRepository eventReviewRepository;
    private final EventQuestionRepository eventQuestionRepository;
    private final EventQuestionAnswerRepository eventQuestionAnswerRepository;

    @Autowired
    public EventProductServiceImpl(EventProductRepository eventProductRepository,
                                   EventReviewRepository eventReviewRepository,
                                   EventQuestionRepository eventQuestionRepository,
                                   EventQuestionAnswerRepository eventQuestionAnswerRepository) {
        this.eventProductRepository = eventProductRepository;
        this.eventReviewRepository = eventReviewRepository;
        this.eventQuestionRepository = eventQuestionRepository;
        this.eventQuestionAnswerRepository = eventQuestionAnswerRepository;
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
                    .imageFiles(eventProduct.getImageFiles())
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
                    .imageFiles(eventProduct.getImageFiles())
                    .build();

            eventProductResponseDtoList.add(dto);
        }
        return eventProductResponseDtoList;
    }



    @Override
    public EventProductResponseDto saveEventProductDto(EventProductDto eventProductDto, List<MultipartFile> files) throws IOException {
        EventProduct eventProduct = new EventProduct();
        eventProduct.setName(eventProductDto.getName());
        eventProduct.setSort(eventProductDto.getSort());
        eventProduct.setSalesType(eventProductDto.getSalesType());


        if (files==null) {
            eventProduct.setImageFiles(null);
        } else {
            List<ImageFile> forAddImageFile = new ArrayList<>();
            for(MultipartFile file:files) {
                // 파일 저장
                String projectPath = System.getProperty("user.dir") + "\\gguda\\src\\main\\resources\\static\\files";
                UUID uuid = UUID.randomUUID();
                String fileName = uuid + "_" + file.getOriginalFilename();
                File saveFile = new File(projectPath, fileName);
                file.transferTo(saveFile);

                ImageFile imageFile = new ImageFile();
                imageFile.setFilename(fileName);
                imageFile.setFilepath("/files/" + fileName);

                forAddImageFile.add(imageFile);
            }
            eventProduct.setImageFiles(forAddImageFile);

        }


        eventProductRepository.save(eventProduct);

        EventProductResponseDto eventProductResponseDto = new EventProductResponseDto();
        eventProductResponseDto.setId(eventProduct.getId());
        eventProductResponseDto.setSort(eventProduct.getSort());
        eventProductResponseDto.setName(eventProduct.getName());
        eventProductResponseDto.setSalesType(eventProduct.getSalesType());
        eventProductResponseDto.setCreatedAt(eventProduct.getCreatedAt());
        eventProductResponseDto.setUpdatedAt(eventProduct.getUpdatedAt());
        eventProductResponseDto.setImageFiles(eventProduct.getImageFiles());


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
        eventProductResponseDto.setImageFiles(eventProduct.getImageFiles());

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
                    .imageFiles(eventReview.getImageFiles())
                    .build();

            eventReviewResponseDtoList.add(dto);
        }

        return eventReviewResponseDtoList;
    }

    @Override
    public EventProductResponseDto putEventProduct(EventProductDto eventProductDto, Long id) {
        EventProduct eventProduct = eventProductRepository.getById(id);

        eventProduct.setName(eventProductDto.getName());
        eventProduct.setSort(eventProductDto.getSort());
        eventProduct.setSalesType(eventProductDto.getSalesType());

        eventProductRepository.save(eventProduct);

        EventProductResponseDto eventProductResponseDto = new EventProductResponseDto();
        eventProductResponseDto.setId(eventProduct.getId());
        eventProductResponseDto.setName(eventProduct.getName());
        eventProductResponseDto.setSalesType(eventProduct.getSalesType());
        eventProductResponseDto.setSort(eventProduct.getSort());
        eventProductResponseDto.setCreatedAt(eventProduct.getCreatedAt());
        eventProductResponseDto.setUpdatedAt(eventProduct.getUpdatedAt());
        eventProductResponseDto.setImageFiles(eventProduct.getImageFiles());

        return eventProductResponseDto;
    }

    @Override
    public EventProductResponseDto deleteEventProduct(Long id) {
        Optional<EventProduct> optionalEventProduct = eventProductRepository.findById(id);
        EventProduct eventProduct = optionalEventProduct.get();

        eventProduct.setName(null);
        eventProduct.setSalesType(null);
        eventProduct.setSort(null);
        eventProduct.setImageFiles(null);

        eventProductRepository.save(eventProduct);

        EventProductResponseDto eventProductResponseDto = new EventProductResponseDto();
        eventProductResponseDto.setId(eventProduct.getId());
        eventProductResponseDto.setSalesType(eventProduct.getSalesType());
        eventProductResponseDto.setName(eventProduct.getName());
        eventProductResponseDto.setSort(eventProduct.getSort());
        eventProductResponseDto.setCreatedAt(eventProduct.getCreatedAt());
        eventProductResponseDto.setUpdatedAt(eventProduct.getUpdatedAt());
        eventProductResponseDto.setImageFiles(eventProduct.getImageFiles());

        return eventProductResponseDto;
    }

    @Override
    public List<EventQuestionAnswerResponseDto> getEventQuestionAnswer(Long id) {
        // 아이디에해당하는 질문답변리스트받기
        List<EventQuestion> eventQuestions = eventQuestionRepository.findAllByEventProductIdOrderByCreatedAtDesc(id);

        List<EventQuestionAnswerResponseDto> eventQuestionAnswerResponseDtoList = new ArrayList<>();

        for(EventQuestion eventQuestion : eventQuestions){

            Long eventQuestionIdTemp = eventQuestion.getId();
            if (eventQuestionIdTemp != null) {
                EventQuestionAnswer eventQuestionAnswer = eventQuestionAnswerRepository.getByEventQuestionId(eventQuestionIdTemp);

                EventQuestionAnswerResponseDto dto = EventQuestionAnswerResponseDto.builder()
                        .id(eventQuestionAnswer.getId())
                        .content(eventQuestionAnswer.getContent())
                        .privateWhether(eventQuestionAnswer.getPrivateWhether())
                        .eventQuestionId(eventQuestionAnswer.getEventQuestion().getId())
                        .createdAt(eventQuestionAnswer.getCreatedAt())
                        .updatedAt(eventQuestionAnswer.getUpdatedAt())
                        .build();

                eventQuestionAnswerResponseDtoList.add(dto);
            }
        }

        return eventQuestionAnswerResponseDtoList;
    }


}

