package com.springboot.gguda.service;

import com.springboot.gguda.data.dto.OrderHistoryResponseDto;
import com.springboot.gguda.data.dto.QuestionDto;
import com.springboot.gguda.data.dto.QuestionResponseDto;

import java.util.List;

public interface OrderHistoryService {

    List<OrderHistoryResponseDto> getOrderHistoryList(Long id);

}
