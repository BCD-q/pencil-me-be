package com.bcdq.pencilme.interest_mapping.service;

import com.bcdq.pencilme.interest.domain.Interest;
import com.bcdq.pencilme.interest_mapping.domain.InterestMapping;
import com.bcdq.pencilme.interest_mapping.domain.dto.response.InterestMappingResDto;
import com.bcdq.pencilme.interest_mapping.repository.InterestMappingRepository;
import com.bcdq.pencilme.member.domain.Member;
import com.bcdq.pencilme.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InterestMappingService {
    private final MemberRepository memberRepository;
    private final InterestMappingRepository interestMappingRepository;

    /*
    * Member와 Interest간 매핑을 시켜주는 메소드
    * */
    public void relatingObjects(String memberUid, List<Interest> interestList) {
        Member foundMember = findMemberByUid(memberUid);
        List<InterestMapping> interestMappingList = interestList.stream().map(
                interest -> InterestMapping.builder().member(foundMember).interest(interest).build()
        ).collect(Collectors.toList());
        interestMappingRepository.saveAll(interestMappingList);
    }

    /*
    * Member와 연관된 Interest를 모두 찾기
    * */
    public List<InterestMappingResDto.findAllByMember> findAllByMember(String memberUid) {
        Member foundMember = findMemberByUid(memberUid);
        List<InterestMapping> interestMappingList = interestMappingRepository.findAllByMember(foundMember);
        return interestMappingList.stream().map(
                interestMapping ->
                        InterestMappingResDto.findAllByMember.builder()
                                .id(interestMapping.getInterest().getId())
                                .name(interestMapping.getInterest().getKeyword())
                                .build())
                .collect(Collectors.toList());
    }

    /*
    * Member와 Interest의 연관관계 해제
    * */
    public void dissociateObjects(String memberUid, List<Interest> interestList) {
        Member foundMember = findMemberByUid(memberUid);
       List<InterestMapping> interestMappingList =  interestMappingRepository.findAllByMember(foundMember);
       List<Long> missingIds = interestMappingList.stream().map(
               interestMapping -> interestMapping.getInterest().getId()
       ).collect(Collectors.toList());
       List<Long> givenIds = interestList.stream().map(Interest::getId).collect(Collectors.toList());
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

    private Member findMemberByUid(String memberUid) {
        return memberRepository.findByUid(memberUid).orElseThrow(() -> new RuntimeException(""));
    }
}
