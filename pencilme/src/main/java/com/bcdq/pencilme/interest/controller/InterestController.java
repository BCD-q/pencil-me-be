package com.bcdq.pencilme.interest.controller;

import com.bcdq.pencilme.interest.domain.dto.request.InterestReqDto;
import com.bcdq.pencilme.interest.domain.dto.response.InterestResDto;
import com.bcdq.pencilme.interest.service.InterestService;
import com.bcdq.pencilme.common.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Tag(name = "[Interest] 관심사 API", description = "관심사를 데이터베이스에 등록, 수정, 조회, 삭제 할 수 있는 API들의 모음입니다.")
public class InterestController {
    private final InterestService interestService;

    @Operation(summary = "관심사 전체 조회", description = "모든 관심사를 조회하는 API입니다.")
    @GetMapping("/v1/interest")
    public ResponseEntity<CommonResponse<List<InterestResDto.findInterest>>> findingAllInterest() {
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.of("", "", interestService.findAllInterests())
        );
    }

    @Operation(summary = "관심사 생성", description = "관심사를 여러개 생성하는 API입니다.")
    @PostMapping("/v1/interest")
    public ResponseEntity<CommonResponse<List<Long>>> creatingMultipleInterests(
            final @RequestBody InterestReqDto.CreateInterests createInterests
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.of("", "", interestService.createInterests(createInterests))
        );
    }

    @Operation(summary = "관심사 (단 건) 수정", description = "관심사를 (단 건) 수정할 수 있는 API입니다.")
    @PatchMapping("/v1/interest")
    public ResponseEntity<CommonResponse<Long>> modifyingInterest(
            final @RequestParam("id") Long id,
            final @RequestParam("keyword") String keyword
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.of("", "", interestService.modifyInterests(id, keyword))
        );
    }

    @Operation(summary = "관심사 삭제", description = "여러개의 관심사를 삭제할 수 있는 API입니다.")
    @DeleteMapping("/v1/interest/{ids}")
    public ResponseEntity<CommonResponse<List<Long>>> deletingMultipleInterest(
            final @PathVariable("ids") List<Long> ids
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.of("", "", interestService.removeInterests(ids))
        );
    }

}
