package com.bcdq.pencilme.interest_mapping.service;

import com.bcdq.pencilme.interest.domain.Interest;
import com.bcdq.pencilme.interest.repository.InterestRepository;
import com.bcdq.pencilme.interest_mapping.domain.InterestMapping;
import com.bcdq.pencilme.interest_mapping.dto.response.InterestMappingResponse;
import com.bcdq.pencilme.interest_mapping.repository.InterestMappingRepository;
import com.bcdq.pencilme.member.domain.Member;
import com.bcdq.pencilme.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 관심사 매핑 관련 Service
 * 관심사 매핑, 조회, 매핑 해제 요청 처리
 *
 * @author Wonjeong Kim
 */
@Service
@RequiredArgsConstructor
public class InterestMappingService {
    private final MemberRepository memberRepository;
    private final InterestMappingRepository interestMappingRepository;
    private final InterestRepository interestRepository;

    /*
    * Member와 Interest간 매핑을 시켜주는 메소드
    * */
    public void relatingObjects(Long memberId, List<Long> interestList) {
        Member foundMember = findById(memberId);
        List<InterestMapping> interestMappingList = interestRepository.findAllById(interestList).stream()
                .map(interest -> InterestMapping.builder().member(foundMember).interest(interest).build())
                .collect(Collectors.toList());
        interestMappingRepository.saveAll(interestMappingList);
    }

    /*
    * Member와 연관된 Interest를 모두 찾기
    * */
    public List<InterestMappingResponse> findAllByMember(Long memberId) {
        Member foundMember = findById(memberId);
        return interestMappingRepository.findAllByMember(foundMember).stream()
                .map(InterestMappingResponse::from)
                .collect(Collectors.toList());
    }

    /*
     * Member와 Interest의 연관관계 해제
     * */
    public void dissociateObjects(Long memberId, List<Long> interestList) {
        List<Interest> foundInterests = interestRepository.findAllById(interestList);
        Member foundMember = findById(memberId);
        List<InterestMapping> interestMappingList =  interestMappingRepository.findAllByMember(foundMember);
        List<Long> missingIds = interestMappingList.stream().map(
                interestMapping -> interestMapping.getInterest().getId()
        ).collect(Collectors.toList());
        List<Long> givenIds = foundInterests.stream().map(Interest::getId).collect(Collectors.toList());
        for (Long id: givenIds) {
            missingIds.remove(id);
        }
        List<InterestMapping> missingInterestMappingList = new ArrayList<>();

        for (Long id: missingIds) {
            for (InterestMapping interestMapping: interestMappingList) {
                if (interestMapping.getInterest().getId().equals(id)) {
                    missingInterestMappingList.add(interestMapping);
                }
            }
        }

        interestMappingRepository.deleteAll(missingInterestMappingList);
    }

    /**
     * 회원 DB 조회 메서드
     *
     * @param memberId 조회할 회원의 id 값
     * @return Member 조회된 회원
     */
    private Member findById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException(""));
    }
}
