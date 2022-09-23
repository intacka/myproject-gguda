package com.springboot.gguda.data.repository;

import com.springboot.gguda.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
