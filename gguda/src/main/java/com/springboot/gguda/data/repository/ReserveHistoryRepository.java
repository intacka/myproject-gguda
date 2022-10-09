package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.ReserveHistory;
import com.springboot.gguda.data.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReserveHistoryRepository extends JpaRepository<ReserveHistory, Long> {

     List<ReserveHistory> getByMemberIdOrderByCreatedAtDesc(Long memberId);
}
