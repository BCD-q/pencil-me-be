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

/**
 * 관심사 관련 Service
 * 관심사 조회(전체), 추가(여러개), 수정, 삭제 요청 처리
 *
 * @author Wonjeong Kim
 */
@Service
@RequiredArgsConstructor
public class InterestService {
    private final InterestRepository interestRepository;

    /**
     * 관심사 생성 메서드
     * 파라미터로 받은 관심사 요청을 기반으로 여러개의 관심사를 생성합니다
     *
     * @param createInterestRequest 생성할 관심사(들)의 정보를 담은 요청 DTO
     * @return List<InterestResponse> 관심사 응답 DTO를 담은 List
     */
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

    /**
     * 관심사 전체 조회 메서드
     *
     * @return List<InterestResponse> 관심사 응답 DTO를 담은 List
     */
    public List<InterestResponse> readInterests() {
        return interestRepository.findAll().stream()
                .map(InterestResponse::from)
                .collect(Collectors.toList());
    }

    /**
     * 관심사 수정 메서드
     *
     * @param interestId 수정할 관심사의 id 값
     * @param updateInterestRequest 수정할 관심사의 정보를 담은 요청 DTO
     * @return InterestResponse 관심사 응답 DTO
     */
    public InterestResponse updateInterests(Long interestId, UpdateInterestRequest updateInterestRequest) {
        Interest foundInterest = findById(interestId);
        foundInterest.updateKeyword(updateInterestRequest.getKeyword());
        interestRepository.save(foundInterest);
        return InterestResponse.from(foundInterest);
    }

    /**
     * 관심사 (여러개) 삭제 메서드
     *
     * @param ids 삭제할 관심사들의 id 값
     * @return List<Long> 삭제된 관심사들의 id값
     */
    public List<Long> deleteInterests(List<Long> ids) {
        ids.removeIf(id -> !interestRepository.findAll().stream()
                .map(Interest::getId)
                .collect(Collectors.toList())
                .contains(id));
        interestRepository.deleteAllById(ids);
        return ids;
    }

    /**
     * 관심사 DB 조회 메서드
     *
     * @param interestId 조회할 관심사의 id값
     * @return Interest 조회된 관심사
     */
    private Interest findById(Long interestId) {
        return interestRepository.findById(interestId)
                .orElseThrow(() -> new RuntimeException(""));
    }
}
