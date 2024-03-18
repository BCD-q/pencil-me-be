package com.bcdq.pencilme.Interest.service;

import com.bcdq.pencilme.Interest.repository.InterestMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InterestMappingService {
    private final InterestMappingRepository interestMappingRepository;
}
