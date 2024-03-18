package com.bcdq.pencilme.Interest.service;

import com.bcdq.pencilme.Interest.domain.Interest;
import com.bcdq.pencilme.Interest.domain.dto.request.InterestReqDto;
import com.bcdq.pencilme.Interest.repository.InterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class InterestService {
    private final InterestRepository interestRepository;

    public void createInterests(InterestReqDto.CreateInterests interests) {
        List<Interest> foundList = interestRepository.findAll();
        List<Interest> result = Interest.createInterestDTOtoInterestList(interests);
        Iterator<Interest> iteratorResult = result.iterator();

        while (iteratorResult.hasNext()) {
            Interest interest = iteratorResult.next();
            for (Interest db_interest : foundList) {
                if (Objects.equals(interest.getKeyword(), db_interest.getKeyword())) {
                    iteratorResult.remove();
                    break;
                }
            }
        }

        interestRepository.saveAll(result);
    }
}
