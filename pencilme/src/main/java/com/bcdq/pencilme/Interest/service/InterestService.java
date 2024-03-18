package com.bcdq.pencilme.Interest.service;

import com.bcdq.pencilme.Interest.domain.Interest;
import com.bcdq.pencilme.Interest.domain.dto.request.InterestReqDto;
import com.bcdq.pencilme.Interest.repository.InterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InterestService {
    private final InterestRepository interestRepository;

    public void createInterests(InterestReqDto.CreateInterests interests) {
        Set<String> dbKeyword = interestRepository.findAll()
                .stream()
                .map(Interest::getKeyword)
                .collect(Collectors.toSet());
        List<Interest> newInterest = interests.getInterests().stream()
                .filter(keyword -> !dbKeyword.contains(keyword))
                .map(Interest::new)
                .collect(Collectors.toList());
        interestRepository.saveAll(newInterest);
    }

    public void removeInterests(List<Long> ids) {
        List<Interest> foundList = interestRepository.findAll();
        List<Long> foundIdList = foundList.stream().map(Interest::getId).collect(Collectors.toList());
        ids.removeIf(id -> !foundIdList.contains(id));
        interestRepository.deleteAllById(ids);
    }
}
