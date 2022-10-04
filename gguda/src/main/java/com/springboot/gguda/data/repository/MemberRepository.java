package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
//    Optional<Member> findById(Long id);

}
