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

@Tag(name = "[Interest-Mapping] 관심사 매핑 API", description = "회원과 관심사를 매핑을 관리하는 API들의 모음입니다.")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InterestMappingController {
    private final InterestMappingService interestMappingService;

    @Operation(summary = "Member와 Interest간 매핑")
    @GetMapping("/v1/interest-mapping/{interests}")
    public ResponseEntity<CommonResponse<String>> relatingObject(@AuthenticationPrincipal Member currentMember, @PathVariable("interests") List<Long> interests) {
        Long memberId = currentMember.getId();
        interestMappingService.relatingObjects(memberId, interests);
        return CommonResponse.from(ResponseType.여러관심사멤버매핑);
    }

    @Operation(summary = "멤버와 연관된 Interest를 모두 찾기")
    @GetMapping("/v1/interest-mapping")
    public ResponseEntity<CommonResponse<List<InterestMappingResponse>>> findAllByMember(final @AuthenticationPrincipal Member currentMember) {
        Long memberId = currentMember.getId();
        return CommonResponse.of(ResponseType.사용자의모든관심사찾기, interestMappingService.findAllByMember(memberId));
    }

    @Operation(summary = "Member와 Interest간의 연관관계 해제")
    @DeleteMapping("/v1/interest-mapping")
    public ResponseEntity<CommonResponse<String>> dissociateObject(@AuthenticationPrincipal Member currentMember, @RequestParam("interests") List<Long> interests) {
        Long memberId = currentMember.getId();
        interestMappingService.dissociateObjects(memberId, interests);
        return CommonResponse.from(ResponseType.여러관심사매핑해제);
    }
}
