package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
//    Optional<Member> findById(Long id);
    List<Member> findAllByOrderByCreatedAtDesc();

    Optional<Member> findByMemberId(String memberId);
}
