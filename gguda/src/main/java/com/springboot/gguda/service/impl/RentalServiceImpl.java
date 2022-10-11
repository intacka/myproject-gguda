package com.springboot.gguda.service.impl;

import com.springboot.gguda.data.dto.*;
import com.springboot.gguda.data.entity.*;
import com.springboot.gguda.data.repository.*;
import com.springboot.gguda.result.PurchaseResult;
import com.springboot.gguda.service.PurchaseService;
import com.springboot.gguda.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository,
                             MemberRepository memberRepository,
                             ProductRepository productRepository) {
        this.rentalRepository = rentalRepository;
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
    }


    @Override
    public RentalResponseDto createRental(RentalDto rentalDto) {
        Rental rental = new Rental();
        rental.setName(rentalDto.getName());
        rental.setShippingAddress(rentalDto.getShippingAddress());
        rental.setRentalMonth(rentalDto.getRentalMonth());
        rental.setMonthPrice(rentalDto.getMonthPrice());
        rental.setCardNum(rentalDto.getCardNum());
        rental.setExPeriod(rentalDto.getExPeriod());
        rental.setDateOfBirth(rentalDto.getDateOfBirth());
        rental.setPayDate(rentalDto.getPayDate());
        rental.setMember(memberRepository.getById(rentalDto.getMemberId()));
        rental.setProduct(productRepository.getById(rentalDto.getProductId()));
//        rental.setEndDate(rental.getCreatedAt().plusMonths(rental.getRentalMonth()));
        rental.setThisDatePayedState(rentalDto.getThisDatePayedState());

        rentalRepository.save(rental);
        rental.setEndDate(rental.getCreatedAt().plusMonths(rentalDto.getRentalMonth()));

        RentalResponseDto rentalResponseDto = new RentalResponseDto();
        rentalResponseDto.setId(rental.getId());
        rentalResponseDto.setName(rental.getName());
        rentalResponseDto.setShippingAddress(rental.getShippingAddress());
        rentalResponseDto.setRentalMonth(rental.getRentalMonth());
        rentalResponseDto.setMonthPrice(rental.getMonthPrice());
        rentalResponseDto.setCardNum(rental.getCardNum());
        rentalResponseDto.setExPeriod(rental.getExPeriod());
        rentalResponseDto.setDateOfBirth(rental.getDateOfBirth());
        rentalResponseDto.setPayDate(rental.getPayDate());
        rentalResponseDto.setMemberId(rental.getMember().getId());
        rentalResponseDto.setProductId(rental.getProduct().getId());
        rentalResponseDto.setCreatedAt(rental.getCreatedAt());
        rentalResponseDto.setUpdatedAt(rental.getUpdatedAt());
        rentalResponseDto.setEndDate(rental.getEndDate());
        rentalResponseDto.setThisDatePayedState(rental.getThisDatePayedState());

        return rentalResponseDto;
    }

    @Override
    public List<RentalResponseDto> getRentalList(Long memberId) {
        List<Rental> rentals = rentalRepository.findAllByMemberId(memberId);
        List<RentalResponseDto> rentalResponseDtosList = new ArrayList<>();

        for(Rental rental : rentals) {
            RentalResponseDto dto = RentalResponseDto.builder()
                    .id(rental.getId())
                    .name(rental.getName())
                    .shippingAddress(rental.getShippingAddress())
                    .rentalMonth(rental.getRentalMonth())
                    .monthPrice(rental.getRentalMonth())
                    .cardNum(rental.getCardNum())
                    .exPeriod(rental.getExPeriod())
                    .dateOfBirth(rental.getDateOfBirth())
                    .payDate(rental.getPayDate())
                    .memberId(rental.getMember().getId())
                    .productId(rental.getProduct().getId())
                    .endDate(rental.getEndDate())
                    .thisDatePayedState(rental.getThisDatePayedState())
                    .createdAt(rental.getCreatedAt())
                    .updatedAt(rental.getUpdatedAt())
                    .build();

            rentalResponseDtosList.add(dto);
        }

        return rentalResponseDtosList;
    }

    @Override
    public List<RentalResponseDto> getAllRentalList() {
        List<Rental> rentals = rentalRepository.findAllByOrderByCreatedAtDesc();
        List<RentalResponseDto> rentalResponseDtosList = new ArrayList<>();

        for(Rental rental : rentals) {
            RentalResponseDto dto = RentalResponseDto.builder()
                    .id(rental.getId())
                    .name(rental.getName())
                    .shippingAddress(rental.getShippingAddress())
                    .rentalMonth(rental.getRentalMonth())
                    .monthPrice(rental.getRentalMonth())
                    .cardNum(rental.getCardNum())
                    .exPeriod(rental.getExPeriod())
                    .dateOfBirth(rental.getDateOfBirth())
                    .payDate(rental.getPayDate())
                    .memberId(rental.getMember().getId())
                    .productId(rental.getProduct().getId())
                    .endDate(rental.getEndDate())
                    .thisDatePayedState(rental.getThisDatePayedState())
                    .createdAt(rental.getCreatedAt())
                    .updatedAt(rental.getUpdatedAt())
                    .build();

            rentalResponseDtosList.add(dto);
        }

        return rentalResponseDtosList;
    }

    @Override
    public RentalResponseDto getRentalDetail(Long rentalId) {
        Rental rental = rentalRepository.getById(rentalId);

        RentalResponseDto rentalResponseDto = new RentalResponseDto();
        rentalResponseDto.setId(rental.getId());
        rentalResponseDto.setName(rental.getName());
        rentalResponseDto.setShippingAddress(rental.getShippingAddress());
        rentalResponseDto.setRentalMonth(rental.getRentalMonth());
        rentalResponseDto.setMonthPrice(rental.getMonthPrice());
        rentalResponseDto.setCardNum(rental.getCardNum());
        rentalResponseDto.setExPeriod(rental.getExPeriod());
        rentalResponseDto.setDateOfBirth(rental.getDateOfBirth());
        rentalResponseDto.setPayDate(rental.getPayDate());
        rentalResponseDto.setMemberId(rental.getMember().getId());
        rentalResponseDto.setProductId(rental.getProduct().getId());
        rentalResponseDto.setCreatedAt(rental.getCreatedAt());
        rentalResponseDto.setUpdatedAt(rental.getUpdatedAt());
        rentalResponseDto.setEndDate(rental.getEndDate());
        rentalResponseDto.setThisDatePayedState(rental.getThisDatePayedState());

        return rentalResponseDto;
    }
}
