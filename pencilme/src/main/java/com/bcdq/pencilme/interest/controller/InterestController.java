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

@Tag(name = "[Interest] 관심사 API", description = "관심사를 데이터베이스에 등록, 수정, 조회, 삭제 할 수 있는 API들의 모음입니다.")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InterestController {
    private final InterestService interestService;

    @Operation(summary = "관심사 생성", description = "관심사를 여러개 생성하는 API입니다.")
    @PostMapping("/v1/interest")
    public ResponseEntity<CommonResponse<List<InterestResponse>>> addInterests(@Valid @RequestBody CreateInterestRequest createInterestRequest) {
        List<InterestResponse> interestResponses = interestService.createInterests(createInterestRequest);
        return CommonResponse.of(ResponseType.여러관심사생성, interestResponses);
    }

    @Operation(summary = "관심사 전체 조회", description = "모든 관심사를 조회하는 API입니다.")
    @GetMapping("/v1/interest")
    public ResponseEntity<CommonResponse<List<InterestResponse>>> findInterests() {
        List<InterestResponse> interestResponses = interestService.readInterests();
        return CommonResponse.of(ResponseType.관심사전체조회, interestResponses);
    }

    @Operation(summary = "관심사 (단 건) 수정", description = "관심사를 (단 건) 수정할 수 있는 API입니다.")
    @PutMapping("/v1/interest/{interestId}")
    public ResponseEntity<CommonResponse<InterestResponse>> modifyInterest(@PathVariable("interestId") Long interestId, @Valid @RequestBody UpdateInterestRequest updateInterestRequest) {
        InterestResponse interestResponse = interestService.updateInterests(interestId, updateInterestRequest);
        return CommonResponse.of(ResponseType.관심사단건수정, interestResponse);
    }

    @Operation(summary = "관심사 삭제", description = "여러개의 관심사를 삭제할 수 있는 API입니다.")
    @DeleteMapping("/v1/interest/{interestIds}")
    public ResponseEntity<CommonResponse<List<Long>>> removeInterests(@PathVariable("interestIds") List<Long> interestIds) {
        List<Long> removedIds = interestService.deleteInterests(interestIds);
        return CommonResponse.of(ResponseType.여러관심사삭제, removedIds);
    }

}
