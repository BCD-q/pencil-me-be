package com.bcdq.pencilme.member.service;

import com.bcdq.pencilme.member.domain.Member;
import com.bcdq.pencilme.member.dto.request.SignInMemberRequest;
import com.bcdq.pencilme.member.dto.request.SignUpMemberRequest;
import com.bcdq.pencilme.member.dto.request.UpdateMemberRequest;
import com.bcdq.pencilme.member.dto.response.MemberResponse;
import com.bcdq.pencilme.member.dto.response.MemberSignInResponse;
import com.bcdq.pencilme.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 회원 관련 Service
 * 회원 조회(단건, 전체), 추가(가입), 로그인, 수정, 삭제(탈퇴) 요청 처리
 *
 * @author Juwon Lee
 */
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원 가입 메서드
     *
     * @param signUpMemberRequest 생성할 회원의 정보를 담은 요청 DTO
     * @return MemberResponse 회원 응답 DTO
     */
    public MemberResponse createMember(SignUpMemberRequest signUpMemberRequest) {
        Member member = SignUpMemberRequest.toEntity(signUpMemberRequest);
        memberRepository.save(member);
        return MemberResponse.from(member);
    }

    /**
     * 회원 로그인 메서드
     *
     * @param signInMemberRequest 로그인 할 회원의 정보를 담은 요청 DTO
     * @return MemberSignInResponse 회원 로그인 응답 DTO
     */
    public MemberSignInResponse login(SignInMemberRequest signInMemberRequest) {
        return null;
    }

    /**
     * 회원 단건 조회 메서드
     *
     * @param memberId 조회할 회원의 id 값
     * @return MemberResponse 회원 응답 DTO
     */
    public MemberResponse readMember(Long memberId) {
        return MemberResponse.from(findById(memberId));
    }

    /**
     * 회원 전체 조회 메서드
     *
     * @return List<MemberResponse> 회원 응답 DTO를 담은 리스트
     */
    public List<MemberResponse> readMemberList() {
        return memberRepository.findAll().stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());
    }

    /**
     * 회원 수정 메서드
     *
     * @param memberId 수정할 회원의 id 값
     * @param updateMemberRequest 수정할 회원의 정보를 담은 요청 DTO
     * @return MemberResponse 회원 응답 DTO
     */
    public MemberResponse updateMember(Long memberId, UpdateMemberRequest updateMemberRequest) {
        Member member = findById(memberId);
        member.updateMember(updateMemberRequest.getUid(), updateMemberRequest.getEmail(), updateMemberRequest.getPassword(), updateMemberRequest.getNickname());
        memberRepository.save(member);
        return MemberResponse.from(member);
    }

    /**
     * 회원 탈퇴 메서드
     *
     * @param memberId 탈퇴할 회원의 id 값
     */
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    /**
     * 회원 DB 조회 메서드
     *
     * @param memberId 조회할 회원의 id 값
     * @return Member 조회된 회원
     */
    private Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(RuntimeException::new);
    }
}
