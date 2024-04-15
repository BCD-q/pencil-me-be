package com.bcdq.pencilme.interest.service;

import com.bcdq.pencilme.interest.domain.Interest;

import com.bcdq.pencilme.interest.dto.request.CreateInterestRequest;
import com.bcdq.pencilme.interest.dto.request.UpdateInterestRequest;
import com.bcdq.pencilme.interest.dto.response.InterestResponse;
import com.bcdq.pencilme.interest.repository.InterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterestService {
    private final InterestRepository interestRepository;

    public List<InterestResponse> createInterests(CreateInterestRequest createInterestRequest) {
        Set<String> existingKeywords = interestRepository.findAll().stream()
                .map(Interest::getKeyword)
                .collect(Collectors.toSet());

        return createInterestRequest.getKeywords().stream()
                .distinct()
                .filter(keyword -> !existingKeywords.contains(keyword))
                .map(Interest::new)
                .peek(interestRepository::save)
                .map(InterestResponse::from)
                .collect(Collectors.toList());
    }

    public List<InterestResponse> findAllInterests() {
        return interestRepository.findAll().stream()
                .map(InterestResponse::from)
                .collect(Collectors.toList());
    }

    public InterestResponse modifyInterests(Long interestId, UpdateInterestRequest updateInterestRequest) {
        Interest foundInterest = findById(interestId);
        foundInterest.updateKeyword(updateInterestRequest.getKeyword());
        interestRepository.save(foundInterest);
        return InterestResponse.from(foundInterest);
    }

    public List<Long> removeInterests(List<Long> ids) {
        ids.removeIf(id -> !interestRepository.findAll().stream()
                .map(Interest::getId)
                .collect(Collectors.toList())
                .contains(id));
        interestRepository.deleteAllById(ids);
        return ids;
    }

    private Interest findById(Long id) {
        return interestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(""));
    }
}
