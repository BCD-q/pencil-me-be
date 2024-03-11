package com.bcdq.pencilme.member.controller;

import com.bcdq.pencilme.common.CommonResponse;
import com.bcdq.pencilme.member.dto.request.CreateMemberRequest;
import com.bcdq.pencilme.member.dto.response.MemberResponse;
import com.bcdq.pencilme.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.bcdq.pencilme.common.ResponseType.*;

/**
 * Member 관련 Controller
 * 사용자 조회, 가입, 수정, 탈퇴 요청
 *
 * @author Juwon Lee
 */
@CrossOrigin // temp
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /**
     * POST /api/v1/members
     * 사용자 가입(회원가입) 메서드
     *
     * @param createMemberRequest 생성할 사용자의 정보를 담은 요청 DTO
     * @return CommonResponse<MemberResponse> 기본 응답 + 사용자 응답 DTO
     */
    @PostMapping("/v1/members")
    public ResponseEntity<CommonResponse<MemberResponse>> addMember(@Valid @RequestBody CreateMemberRequest createMemberRequest) {
        MemberResponse memberResponse = null;
        return CommonResponse.of(회원가입, memberResponse);
    }

    /**
     * GET /api/v1/members/:memberId
     * 사용자 정보 조회 메서드
     *
     * @param memberId 조회할 사용자의 id값
     * @return CommonResponse<MemberResponse> 기본 응답 + 사용자 응답 DTO
     */
    @GetMapping("/v1/members/{memberId}")
    public ResponseEntity<CommonResponse<MemberResponse>> getMember(@PathVariable("memberId") Long memberId) {
        MemberResponse memberResponse = null;
        return CommonResponse.of(회원조회, memberResponse);
    }

    /**
     * GET /api/v1/members
     * 사용자 전체 정보 조회 메서드 (관리자용)
     *
     * @return CommonResponse<List<MemberResponse>> 기본 응답 + 사용자 응답 DTO 타입 리스트
     */
    @GetMapping("/v1/members")
    public ResponseEntity<CommonResponse<List<MemberResponse>>> getMemberList() {
        List<MemberResponse> memberResponse = null;
        return CommonResponse.of(회원조회, memberResponse);
    }

    /**
     * PUT /api/v1/members/:memberId
     * 사용자 정보 수정 메서드
     *
     * @param memberId 수정할 사용자의 id값
     * @return CommonResponse<MemberResponse> 기본 응답 + 사용자 응답 DTO
     */
    @PutMapping("/v1/members/{memberId}")
    public ResponseEntity<CommonResponse<MemberResponse>> modifyMember(@PathVariable("memberId") Long memberId) {
        MemberResponse memberResponse = null;
        return CommonResponse.of(회원수정, memberResponse);
    }

    /**
     * DELETE /api/v1/members/:memberId
     * 사용자 탈퇴(회원탈퇴) 메서드
     *
     * @param memberId 삭제할 사용자의 id값
     * @return CommonResponse 기본 응답
     */
    @DeleteMapping("/v1/members/{memberId}")
    public ResponseEntity<CommonResponse<String>> removeMember(@PathVariable("memberId") Long memberId) {
        return CommonResponse.from(회원탈퇴);
    }
}
