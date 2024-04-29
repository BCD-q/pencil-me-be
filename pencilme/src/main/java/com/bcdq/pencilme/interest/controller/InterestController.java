package com.bcdq.pencilme.interest.controller;

import com.bcdq.pencilme.common.ResponseType;
import com.bcdq.pencilme.interest.dto.request.CreateInterestRequest;
import com.bcdq.pencilme.interest.dto.request.UpdateInterestRequest;
import com.bcdq.pencilme.interest.dto.response.InterestResponse;
import com.bcdq.pencilme.interest.service.InterestService;
import com.bcdq.pencilme.common.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 관심사 관련 Controller
 * 관심사 조회, 생성, 수정, 삭제 요청
 *
 * @author Wonjeong Kim
 */
@Tag(name = "[Interest] 관심사 API", description = "관심사를 데이터베이스에 등록, 수정, 조회, 삭제 할 수 있는 API들의 모음입니다.")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InterestController {
    private final InterestService interestService;

    /**
     * POST /api/v1/interests
     * 관심사 생성 메서드
     *
     * @param createInterestRequest 생성할 관심사들을 담은 요청 DTO
     * @return CommonResponse<List<InterestResponse>> 기본 응답 + 관심사 응답를 DTO를 담은 List
     */
    @Operation(summary = "관심사 생성", description = "관심사를 여러개 생성하는 API입니다.")
    @PostMapping("/v1/interests")
    public ResponseEntity<CommonResponse<List<InterestResponse>>> addInterests(@Valid @RequestBody CreateInterestRequest createInterestRequest) {
        List<InterestResponse> interestResponses = interestService.createInterests(createInterestRequest);
        return CommonResponse.of(ResponseType.여러관심사생성, interestResponses);
    }

    /**
     * GET /v1/interests
     * 관심사 전체 조회 메서드
     *
     * @return CommonResponse<List<InterestResponse>> 기본 응답 + 관심사 응답 DTO를 담은 List
     */
    @Operation(summary = "관심사 전체 조회", description = "모든 관심사를 조회하는 API입니다.")
    @GetMapping("/v1/interests")
    public ResponseEntity<CommonResponse<List<InterestResponse>>> findInterests() {
        List<InterestResponse> interestResponses = interestService.readInterests();
        return CommonResponse.of(ResponseType.관심사전체조회, interestResponses);
    }

    /**
     * PUT /v1/interests/:interestId
     * 관심사 수정 (단건) 메서드
     *
     * @param interestId 수정할 관심사의 id 값
     * @param updateInterestRequest 수정할 관심사의 내용을 담은 요청 DTO
     * @return CommonResponse<InterestResponse> 기본 응답 + 그룹 응답 DTO
     */
    @Operation(summary = "관심사 (단 건) 수정", description = "관심사를 (단 건) 수정할 수 있는 API입니다.")
    @PutMapping("/v1/interests/{interestId}")
    public ResponseEntity<CommonResponse<InterestResponse>> modifyInterest(@PathVariable("interestId") Long interestId, @Valid @RequestBody UpdateInterestRequest updateInterestRequest) {
        InterestResponse interestResponse = interestService.updateInterests(interestId, updateInterestRequest);
        return CommonResponse.of(ResponseType.관심사단건수정, interestResponse);
    }

    /**
     * DELETE /v1/interests/:interestIds
     * 관심사 삭제 (여러개) 메서드
     *
     * @param interestIds 삭제할 관심사의 id값
     * @return CommonResponse<List<Long>> 기본 응답 + 삭제한 관심사의 id값 리스트
     */
    @Operation(summary = "관심사 삭제", description = "여러개의 관심사를 삭제할 수 있는 API입니다.")
    @DeleteMapping("/v1/interests/{interestIds}")
    public ResponseEntity<CommonResponse<List<Long>>> removeInterests(@PathVariable("interestIds") List<Long> interestIds) {
        List<Long> removedIds = interestService.deleteInterests(interestIds);
        return CommonResponse.of(ResponseType.여러관심사삭제, removedIds);
    }

}
