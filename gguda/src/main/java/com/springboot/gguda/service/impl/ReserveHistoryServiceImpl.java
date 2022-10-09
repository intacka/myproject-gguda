package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.*;
import com.springboot.gguda.data.entity.Member;
import com.springboot.gguda.data.entity.Product;
import com.springboot.gguda.data.entity.Question;
import com.springboot.gguda.data.entity.ReserveHistory;
import com.springboot.gguda.data.repository.MemberRepository;
import com.springboot.gguda.data.repository.ProductRepository;
import com.springboot.gguda.data.repository.QuestionRepository;
import com.springboot.gguda.data.repository.ReserveHistoryRepository;
import com.springboot.gguda.service.QuestionService;
import com.springboot.gguda.service.ReserveHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReserveHistoryServiceImpl implements ReserveHistoryService {

    private final ReserveHistoryRepository reserveHistoryRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public ReserveHistoryServiceImpl(ReserveHistoryRepository reserveHistoryRepository,
                                     MemberRepository memberRepository) {
        this.reserveHistoryRepository = reserveHistoryRepository;
        this.memberRepository = memberRepository;
    }


    @Override
    public List<ReserveHistoryResponseDto> getAllReserveHistory(Long memberId) {
        List<ReserveHistory> reserveHistories = reserveHistoryRepository.getByMemberIdOrderByCreatedAtDesc(memberId);
        List<ReserveHistoryResponseDto> reserveHistoryDtoList = new ArrayList<>(); // 2

        for(ReserveHistory reserveHistory : reserveHistories){ // 3
            ReserveHistoryResponseDto dto = ReserveHistoryResponseDto.builder()
                    .id(reserveHistory.getId())
                    .reason(reserveHistory.getReason())
                    .historyPrice(reserveHistory.getHistoryPrice())
                    .type(reserveHistory.getType())
                    .memberId(reserveHistory.getMember().getId())
                    .createdAt(reserveHistory.getCreatedAt())
                    .updatedAt(reserveHistory.getUpdatedAt())
                    .build();

            reserveHistoryDtoList.add(dto);
        }

        return reserveHistoryDtoList;
    }

    @Override
    public ReserveHistoryResponseDto createReserveHistory(ReserveHistoryDto reserveHistoryDto) {

        Member thisMember = memberRepository.getById(reserveHistoryDto.getMemberId());

        ReserveHistory reserveHistory = new ReserveHistory();
        reserveHistory.setReason(reserveHistoryDto.getReason());
        reserveHistory.setHistoryPrice(reserveHistoryDto.getHistoryPrice());
        reserveHistory.setType(reserveHistoryDto.getType());
        reserveHistory.setMember(thisMember);

        reserveHistoryRepository.save(reserveHistory);

        thisMember.setReserves(thisMember.getReserves() + reserveHistory.getHistoryPrice());           // 적립금도 실제로 변동해줘야한다.


        ReserveHistoryResponseDto reserveHistoryResponseDto = new ReserveHistoryResponseDto();
        reserveHistoryResponseDto.setId(reserveHistory.getId());
        reserveHistoryResponseDto.setReason(reserveHistory.getReason());
        reserveHistoryResponseDto.setHistoryPrice(reserveHistory.getHistoryPrice());
        reserveHistoryResponseDto.setType(reserveHistory.getType());
        reserveHistoryResponseDto.setMemberId(reserveHistory.getMember().getId());
        reserveHistoryResponseDto.setCreatedAt(reserveHistory.getCreatedAt());
        reserveHistoryResponseDto.setUpdatedAt(reserveHistory.getUpdatedAt());

        return reserveHistoryResponseDto;
    }

    @Override
    public void createSigninReserveHistory(Long memberId) {
        Member thisMember = memberRepository.getById(memberId);
        thisMember.setReserves(thisMember.getReserves() + 1000);           // 적립금 실제로 변동. (1000원추가)

        ReserveHistory reserveHistory = new ReserveHistory();
        reserveHistory.setReason("회원가입 축하 적립금");
        reserveHistory.setHistoryPrice(1000);
        reserveHistory.setType(0);
        reserveHistory.setMember(thisMember);

        reserveHistoryRepository.save(reserveHistory);                      // 적립금내역도 추가완료.
    }

    @Override
    public void createReviewReserveHistory(Long memberId) {
        Member thisMember = memberRepository.getById(memberId);
        Integer totalprice = thisMember.getReserves() + 100;
        thisMember.setReserves(totalprice);           // 적립금 실제로 변동. (50원추가)

        ReserveHistory reserveHistory = new ReserveHistory();
        reserveHistory.setReason("후기작성 적립금");
        reserveHistory.setHistoryPrice(100);
        reserveHistory.setType(0);
        reserveHistory.setMember(thisMember);

        reserveHistoryRepository.save(reserveHistory);                      // 적립금내역도 추가완료.
    }

    @Override
    public void createPurchaseReserveHistory(Integer reservePrice, Long memberId) {
        Member thisMember = memberRepository.getById(memberId);
        thisMember.setReserves(thisMember.getReserves() + reservePrice);           // 적립금 실제로 변동. 총가격의 1%추가

        ReserveHistory reserveHistory = new ReserveHistory();
        reserveHistory.setReason("구매금액의 1% 적립");
        reserveHistory.setHistoryPrice(reservePrice);
        reserveHistory.setType(0); // 지급
        reserveHistory.setMember(thisMember);

        reserveHistoryRepository.save(reserveHistory);                      // 적립금내역도 추가완료.
    }
}
