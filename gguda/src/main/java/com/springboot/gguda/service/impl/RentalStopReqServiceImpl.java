package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.RentalStopReqDto;
import com.springboot.gguda.data.dto.RentalStopReqResponseDto;
import com.springboot.gguda.data.entity.RentalStopReq;
import com.springboot.gguda.data.repository.MemberRepository;
import com.springboot.gguda.data.repository.ProductRepository;
import com.springboot.gguda.data.repository.RentalStopReqRepository;
import com.springboot.gguda.service.RentalStopReqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentalStopReqServiceImpl implements RentalStopReqService {

    private final RentalStopReqRepository rentalStopReqRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Autowired
    public RentalStopReqServiceImpl(RentalStopReqRepository rentalStopReqRepository,
                                    MemberRepository memberRepository,
                                    ProductRepository productRepository) {
        this.rentalStopReqRepository = rentalStopReqRepository;
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
    }

    @Override
    public RentalStopReqResponseDto createRentalStopReq(RentalStopReqDto rentalStopReqDto) {
        RentalStopReq rentalStopReq = new RentalStopReq();
        rentalStopReq.setEtcReq(rentalStopReqDto.getStopReqDate());
        rentalStopReq.setEtcReq(rentalStopReqDto.getEtcReq());
        rentalStopReq.setMember(memberRepository.getById(rentalStopReqDto.getMemberId()));
        rentalStopReq.setProduct(productRepository.getById(rentalStopReqDto.getProductId()));

        rentalStopReqRepository.save(rentalStopReq);

        RentalStopReqResponseDto rentalStopReqResponseDto = new RentalStopReqResponseDto();

        rentalStopReqResponseDto.setId(rentalStopReq.getId());
        rentalStopReqResponseDto.setEtcReq(rentalStopReq.getStopReqDate());
        rentalStopReqResponseDto.setEtcReq(rentalStopReq.getEtcReq());
        rentalStopReqResponseDto.setMemberId(rentalStopReq.getMember().getId());
        rentalStopReqResponseDto.setProductId(rentalStopReq.getProduct().getId());
        rentalStopReqResponseDto.setCreatedAt(rentalStopReq.getCreatedAt());
        rentalStopReqResponseDto.setUpdatedAt(rentalStopReq.getUpdatedAt());

        return rentalStopReqResponseDto;
    }

    @Override
    public List<RentalStopReqResponseDto> getAllRentalStopReq() {
        List<RentalStopReq> rentalStopReqs = rentalStopReqRepository.findAllByOrderByCreatedAtDesc();
        List<RentalStopReqResponseDto> rentalStopReqResponseDtosList = new ArrayList<>();

        for(RentalStopReq rentalStopReq : rentalStopReqs) {
            RentalStopReqResponseDto dto = RentalStopReqResponseDto.builder()
                    .id(rentalStopReq.getId())
                    .stopReqDate(rentalStopReq.getStopReqDate())
                    .etcReq(rentalStopReq.getEtcReq())
                    .memberId(rentalStopReq.getMember().getId())
                    .productId(rentalStopReq.getProduct().getId())
                    .createdAt(rentalStopReq.getCreatedAt())
                    .updatedAt(rentalStopReq.getUpdatedAt())
                    .build();

            rentalStopReqResponseDtosList.add(dto);
        }

        return rentalStopReqResponseDtosList;
    }
}
