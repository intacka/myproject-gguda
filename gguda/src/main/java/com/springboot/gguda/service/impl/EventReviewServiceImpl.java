package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.EventReviewDto;
import com.springboot.gguda.data.dto.EventReviewResponseDto;
import com.springboot.gguda.data.dto.ReviewDto;
import com.springboot.gguda.data.dto.ReviewResponseDto;
import com.springboot.gguda.data.entity.EventReview;
import com.springboot.gguda.data.entity.ImageFile;
import com.springboot.gguda.data.entity.Review;
import com.springboot.gguda.data.repository.*;
import com.springboot.gguda.service.EventReviewService;
import com.springboot.gguda.service.ReviewService;
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
import java.util.UUID;

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
    public EventReviewResponseDto saveEventReviewDto(EventReviewDto eventReviewDto, List<MultipartFile> files) throws IOException {
        EventReview eventReview = new EventReview();
        eventReview.setContent(eventReviewDto.getContent());
        eventReview.setStars(eventReviewDto.getStars());
        eventReview.setEventProduct(eventProductRepository.findById(eventReviewDto.getEventProductId()).get());
        eventReview.setMember(memberRepository.findById(eventReviewDto.getMemberId()).get());



        if (files==null) {
            eventReview.setImageFiles(null);
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
            eventReview.setImageFiles(forAddImageFile);

        }


        eventReviewRepository.save(eventReview);

        EventReviewResponseDto eventReviewResponseDto = new EventReviewResponseDto();
        eventReviewResponseDto.setId(eventReview.getId());
        eventReviewResponseDto.setContent(eventReview.getContent());
        eventReviewResponseDto.setStars(eventReview.getStars());
        eventReviewResponseDto.setCreatedAt(eventReview.getCreatedAt());
        eventReviewResponseDto.setUpdatedAt(eventReview.getUpdatedAt());
        eventReviewResponseDto.setEventProductId(eventReview.getEventProduct().getId());
        eventReviewResponseDto.setMemberId(eventReview.getMember().getId());
        eventReviewResponseDto.setImageFiles(eventReview.getImageFiles());

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
                    .imageFiles(eventReview.getImageFiles())
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
                    .imageFiles(eventReview.getImageFiles())
                    .build();

            eventReviewResponseDtoList.add(dto);
        }

        return eventReviewResponseDtoList;
    }

    @Override
    public EventReviewResponseDto putEventReview(EventReviewDto eventReviewDto, Long id) {
        EventReview eventReview = eventReviewRepository.getById(id);

        eventReview.setContent(eventReviewDto.getContent());
        eventReview.setStars(eventReviewDto.getStars());

        eventReviewRepository.save(eventReview);

        EventReviewResponseDto eventReviewResponseDto = new EventReviewResponseDto();
        eventReviewResponseDto.setId(eventReview.getId());
        eventReviewResponseDto.setContent(eventReview.getContent());
        eventReviewResponseDto.setStars(eventReview.getStars());
        eventReviewResponseDto.setEventProductId(eventReview.getEventProduct().getId());
        eventReviewResponseDto.setMemberId(eventReview.getMember().getId());
        eventReviewResponseDto.setCreatedAt(eventReview.getCreatedAt());
        eventReviewResponseDto.setUpdatedAt(eventReview.getUpdatedAt());
        eventReviewResponseDto.setImageFiles(eventReview.getImageFiles());

        return eventReviewResponseDto;
    }

    @Override
    public void deleteEventReview(Long id) {
        eventReviewRepository.deleteById(id);
    }

    @Override
    public List<EventReviewResponseDto> getAllEventReview() {
        List<EventReview> eventReviews = eventReviewRepository.findAllByOrderByCreatedAtDesc();

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
                    .imageFiles(eventReview.getImageFiles())
                    .build();

            eventReviewResponseDtoList.add(dto);
        }

        return eventReviewResponseDtoList;
    }

    @Override
    public EventReviewResponseDto getEventReview(Long id) {
        EventReview eventReview = eventReviewRepository.getById(id);

        EventReviewResponseDto eventReviewResponseDto = new EventReviewResponseDto();
        eventReviewResponseDto.setId(eventReview.getId());
        eventReviewResponseDto.setContent(eventReview.getContent());
        eventReviewResponseDto.setStars(eventReview.getStars());
        eventReviewResponseDto.setCreatedAt(eventReview.getCreatedAt());
        eventReviewResponseDto.setUpdatedAt(eventReview.getUpdatedAt());
        eventReviewResponseDto.setEventProductId(eventReview.getEventProduct().getId());
        eventReviewResponseDto.setMemberId(eventReview.getMember().getId());
        eventReviewResponseDto.setImageFiles(eventReview.getImageFiles());

        return eventReviewResponseDto;
    }


}
