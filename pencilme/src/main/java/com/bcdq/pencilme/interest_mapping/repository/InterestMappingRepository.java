package com.bcdq.pencilme.interest_mapping.repository;

import com.bcdq.pencilme.interest_mapping.domain.InterestMapping;
import com.bcdq.pencilme.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestMappingRepository extends JpaRepository<InterestMapping, Long> {
    List<InterestMapping> findAllByMember(Member member);
}
