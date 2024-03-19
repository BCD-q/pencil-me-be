package com.bcdq.pencilme.Interest.service;

import com.bcdq.pencilme.Interest.domain.Interest;
import com.bcdq.pencilme.Interest.domain.dto.request.InterestReqDto;
import com.bcdq.pencilme.Interest.domain.dto.response.InterestResDto;
import com.bcdq.pencilme.Interest.repository.InterestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class InterestService {
    private final InterestRepository interestRepository;

    public List<Long> createInterests(InterestReqDto.CreateInterests interestsDto) {
        Set<String> dbKeyword = interestRepository.findAll()
                .stream()
                .map(Interest::getKeyword)
                .collect(Collectors.toSet());
        List<Interest> newInterest = interestsDto.getKeywords().stream()
                .distinct()
                .filter(keyword -> !dbKeyword.contains(keyword))
                .map(Interest::new)
                .collect(Collectors.toList());
        List<Interest> result = interestRepository.saveAll(newInterest);
        return result.stream()
                .map(Interest::getId)
                .collect(Collectors.toList());
    }

    public List<InterestResDto.findAllInterests> findAllInterests() {
        List<Interest> foundList = interestRepository.findAll();
        return foundList.stream()
                .map(Interest::createResponseDto)
                .collect(Collectors.toList());
    }

    public Long modifyInterests(Long id, String keyword) {
        Interest foundInterest = interestRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
        Interest result = interestRepository.save(foundInterest.updateKeywordByString(keyword));
        return result.getId();
    }

    public List<Long> removeInterests(List<Long> ids) {
        List<Interest> foundList = interestRepository.findAll();
        List<Long> foundIdList = foundList.stream().map(Interest::getId).collect(Collectors.toList());
        ids.removeIf(id -> !foundIdList.contains(id));
        interestRepository.deleteAllById(ids);
        return ids;
    }
}
