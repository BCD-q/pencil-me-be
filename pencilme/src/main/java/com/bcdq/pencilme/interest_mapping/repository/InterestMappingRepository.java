package com.bcdq.pencilme.interest_mapping.repository;

import com.bcdq.pencilme.interest_mapping.domain.InterestMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestMappingRepository extends JpaRepository<InterestMapping, Long> {
}
