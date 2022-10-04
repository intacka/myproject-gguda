package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.OrderHistoryResponseDto;
import com.springboot.gguda.data.dto.ReviewDto;
import com.springboot.gguda.data.dto.ReviewResponseDto;
import com.springboot.gguda.data.entity.OrderHistory;
import com.springboot.gguda.data.entity.Review;
import com.springboot.gguda.data.repository.MemberRepository;
import com.springboot.gguda.data.repository.OrderHistoryRepository;
import com.springboot.gguda.data.repository.ProductRepository;
import com.springboot.gguda.data.repository.ReviewRepository;
import com.springboot.gguda.service.OrderHistoryService;
import com.springboot.gguda.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

    private final OrderHistoryRepository orderHistoryRepository;

    @Autowired
    public OrderHistoryServiceImpl(OrderHistoryRepository orderHistoryRepository) {
        this.orderHistoryRepository = orderHistoryRepository;
    }


    @Override
    public List<OrderHistoryResponseDto> getOrderHistoryList(Long id) {
        List<OrderHistory> orderHistories = orderHistoryRepository.findAllByMemberIdOrderByCreatedAtDesc(id);

        List<OrderHistoryResponseDto> orderHistoryResponseDtoList = new ArrayList<>();

        for(OrderHistory orderHistoriy : orderHistories){
            OrderHistoryResponseDto dto = OrderHistoryResponseDto.builder()
                    .id(orderHistoriy.getId())
                    .state(orderHistoriy.getState())
                    .createdAt(orderHistoriy.getCreatedAt())
                    .updatedAt(orderHistoriy.getUpdatedAt())
                    .build();

            orderHistoryResponseDtoList.add(dto);
        }

        return orderHistoryResponseDtoList;
    }
}
