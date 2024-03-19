package com.bcdq.pencilme.interest_mapping.service;

import com.bcdq.pencilme.interest_mapping.repository.InterestMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InterestMappingService {
    private final InterestMappingRepository interestMappingRepository;


}
