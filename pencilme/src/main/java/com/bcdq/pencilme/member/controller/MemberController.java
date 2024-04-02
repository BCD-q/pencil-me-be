package com.bcdq.pencilme.member.controller;

import com.bcdq.pencilme.common.CommonResponse;
import com.bcdq.pencilme.member.dto.request.SignInMemberRequest;
import com.bcdq.pencilme.member.dto.request.SignUpMemberRequest;
import com.bcdq.pencilme.member.dto.request.UpdateMemberRequest;
import com.bcdq.pencilme.member.dto.response.MemberResponse;
import com.bcdq.pencilme.member.dto.response.MemberSignInResponse;
import com.bcdq.pencilme.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.bcdq.pencilme.common.ResponseType.*;

/**
 * Member 관련 Controller
 * 회원 조회, 가입, 수정, 탈퇴 요청
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
     * 회원 가입 메서드
     *
     * @param createMemberRequest 생성할 회원의 정보를 담은 요청 DTO
     * @return CommonResponse<MemberResponse> 기본 응답 + 회원 응답 DTO
     */
    @PostMapping("/v1/members")
    @Operation(summary = "회원 가입", description = "회원 가입을 위한 정보를 Body에 담아서 보내주세요")
    public ResponseEntity<CommonResponse<MemberResponse>> addMember(@Valid @RequestBody SignUpMemberRequest createMemberRequest) {
        MemberResponse memberResponse = memberService.createMember(createMemberRequest);
        return CommonResponse.of(회원가입, memberResponse);
    }

    /**
     * POST /api/v1/members/login
     * 회원 로그인 메서드
     *
     * @param signInMemberRequest 로그인할 회원의 정보를 담은 요청 DTO
     * @return CommonResponse<MemberSignInResponse> 기본 응답 + 회원 로그인 응답 DTO
     */
    @PostMapping("/v1/members/login")
    @Operation(summary = "회원 로그인", description = "회원 로그인을 위한 정보를 Body에 담아서 보내주세요")
    public ResponseEntity<CommonResponse<MemberSignInResponse>> login(@Valid @RequestBody SignInMemberRequest signInMemberRequest) {
        MemberSignInResponse memberSignInResponse = memberService.login(signInMemberRequest);
        return CommonResponse.of(회원로그인, memberSignInResponse);
    }

    /**
     * GET /api/v1/members/:memberId
     * 회원 정보 조회 메서드
     *
     * @param memberId 조회할 회원의 id값
     * @return CommonResponse<MemberResponse> 기본 응답 + 회원 응답 DTO
     */
    @GetMapping("/v1/members/{memberId}")
    @Operation(summary = "회원 조회", description = "조회 할 회원의 식별자를 PathVariable로 보내주세요")
    public ResponseEntity<CommonResponse<MemberResponse>> getMember(@PathVariable("memberId") Long memberId) {
        MemberResponse memberResponse = memberService.readMember(memberId);
        return CommonResponse.of(회원조회, memberResponse);
    }

    /**
     * GET /api/v1/members
     * 회원 전체 정보 조회 메서드 (관리자용)
     *
     * @return CommonResponse<List<MemberResponse>> 기본 응답 + 회원 응답 DTO 타입 리스트
     */
    @GetMapping("/v1/members")
    @Operation(summary = "회원 전체 조회(관리자용)", description = "회원 전체의 정보를 가져옵니다")
    public ResponseEntity<CommonResponse<List<MemberResponse>>> getMemberList() {
        List<MemberResponse> memberResponse = memberService.readMemberList();
        return CommonResponse.of(회원조회, memberResponse);
    }

    /**
     * PUT /api/v1/members/:memberId
     * 회원 정보 수정 메서드
     *
     * @param memberId 수정할 회원의 id값
     * @return CommonResponse<MemberResponse> 기본 응답 + 회원 응답 DTO
     */
    @PutMapping("/v1/members/{memberId}")
    @Operation(summary = "회원 정보 수정", description = "정보를 수정할 회원의 식별자는 PathVaraible로, 수정 할 정보는 Body에 담아서 보내주세요")
    public ResponseEntity<CommonResponse<MemberResponse>> modifyMember(@PathVariable("memberId") Long memberId, @Valid @RequestBody UpdateMemberRequest updateMemberRequest) {
        MemberResponse memberResponse = memberService.updateMember(memberId, updateMemberRequest);
        return CommonResponse.of(회원수정, memberResponse);
    }

    /**
     * DELETE /api/v1/members/:memberId
     * 회원 탈퇴 메서드
     *
     * @param memberId 삭제할 회원의 id값
     * @return CommonResponse 기본 응답
     */
    @DeleteMapping("/v1/members/{memberId}")
    @Operation(summary = "회원 탈퇴", description = "탈퇴할 회원의 식별자를 PathVariable로 보내주세요")
    public ResponseEntity<CommonResponse<String>> removeMember(@PathVariable("memberId") Long memberId) {
        memberService.deleteMember(memberId);
        return CommonResponse.from(회원탈퇴);
    }
}
