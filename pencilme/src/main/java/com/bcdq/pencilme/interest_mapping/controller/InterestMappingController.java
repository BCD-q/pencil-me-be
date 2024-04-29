package com.bcdq.pencilme.interest_mapping.controller;

import com.bcdq.pencilme.common.CommonResponse;
import com.bcdq.pencilme.common.ResponseType;
import com.bcdq.pencilme.interest_mapping.dto.response.InterestMappingResponse;
import com.bcdq.pencilme.interest_mapping.service.InterestMappingService;
import com.bcdq.pencilme.member.domain.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 관심사 매핑 관련 Controller
 * 관심사 매핑, 조회, 매핑 해제 요청
 *
 * @author Wonjeong Kim
 */
@Tag(name = "[Interest-Mapping] 관심사 매핑 API", description = "회원과 관심사를 매핑을 관리하는 API들의 모음입니다.")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InterestMappingController {
    private final InterestMappingService interestMappingService;

    /**
     * GET /v1/interestIds-mapping/{interestIds}
     * 사용자 <-> 관심사 매핑 메서드
     *
     * @param currentMember 현재 로그인한 사용자
     * @param interestIds 관심사들의 id 값
     * @return CommonResponse<String> 기본 응답
     */
    @Operation(summary = "Member와 Interest간 매핑")
    @GetMapping("/v1/interestIds-mapping/{interestIds}")
    public ResponseEntity<CommonResponse<String>> relatingObject(@AuthenticationPrincipal Member currentMember, @PathVariable("interestIds") List<Long> interestIds) {
        Long memberId = currentMember.getId();
        interestMappingService.relatingObjects(memberId, interestIds);
        return CommonResponse.from(ResponseType.여러관심사멤버매핑);
    }

    /**
     * GET /api/v1/interest-mapping
     * 사용자와 매핑된 관심사 전체 조회 메서드
     *
     * @param currentMember 현재 로그인한 사용자
     * @return CommonResponse<List<InterestMappingResponse>> 기본 응답 + 관심사 매핑 응답 DTO 리스트
     */
    @Operation(summary = "멤버와 연관된 Interest를 모두 찾기")
    @GetMapping("/v1/interest-mapping")
    public ResponseEntity<CommonResponse<List<InterestMappingResponse>>> findAllByMember(@AuthenticationPrincipal Member currentMember) {
        Long memberId = currentMember.getId();
        return CommonResponse.of(ResponseType.사용자의모든관심사찾기, interestMappingService.findAllByMember(memberId));
    }

    /**
     * DELETE /api/v1/interestIds-mapping
     * 사용자 <-> 관심사 매핑 해제 메서드
     *
     * @param currentMember 현재 로그인한 사용자
     * @param interestIds 관심사들의 id 값
     * @return CommonResponse<String> 기본 응답
     */
    @Operation(summary = "Member와 Interest간의 연관관계 해제")
    @DeleteMapping("/v1/interestIds-mapping")
    public ResponseEntity<CommonResponse<String>> dissociateObject(@AuthenticationPrincipal Member currentMember, @RequestParam("interestIds") List<Long> interestIds) {
        Long memberId = currentMember.getId();
        interestMappingService.dissociateObjects(memberId, interestIds);
        return CommonResponse.from(ResponseType.여러관심사매핑해제);
    }
}
