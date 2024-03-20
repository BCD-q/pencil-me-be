package com.bcdq.pencilme.interest.service;

import com.bcdq.pencilme.interest.domain.Interest;
import static com.bcdq.pencilme.interest.domain.dto.request.InterestReqDto.CreateInterests;
import static com.bcdq.pencilme.interest.domain.dto.response.InterestResDto.findInterest;

import com.bcdq.pencilme.interest.repository.InterestRepository;
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

    public List<Long> createInterests(CreateInterests interestsDto) {
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

    public List<findInterest> findAllInterests() {
        List<Interest> foundList = interestRepository.findAll();
        return foundList.stream()
                .map(findInterest::createResponse)
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
