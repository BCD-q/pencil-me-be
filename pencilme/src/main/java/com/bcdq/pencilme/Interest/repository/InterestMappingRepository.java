package com.bcdq.pencilme.Interest.repository;

import com.bcdq.pencilme.Interest.domain.InterestMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestMappingRepository extends JpaRepository<InterestMapping, Long> {
}
