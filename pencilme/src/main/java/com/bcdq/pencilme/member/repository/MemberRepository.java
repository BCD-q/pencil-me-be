package com.bcdq.pencilme.member.repository;

import com.bcdq.pencilme.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
