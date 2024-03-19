package com.bcdq.pencilme.Interest.controller;

import com.bcdq.pencilme.Interest.domain.dto.request.InterestReqDto;
import com.bcdq.pencilme.Interest.service.InterestService;
import com.bcdq.pencilme.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class InterestController {
    private final InterestService interestService;

    @PostMapping("/v1/interest")
    public ResponseEntity<CommonResponse<List<Long>>> creatingMultipleInterests(
            final @RequestBody InterestReqDto.CreateInterests createInterests
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.of("", "", interestService.createInterests(createInterests))
        );
    }

    @PatchMapping("/v1/interest")
    public ResponseEntity<CommonResponse<Long>> modifyingInterest(
            final @RequestParam("id") Long id,
            final @RequestParam("keyword") String keyword
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.of("", "", interestService.modifyInterests(id, keyword))
        );
    }

    @DeleteMapping("/v1/interest/{ids}")
    public ResponseEntity<CommonResponse<List<Long>>> deletingMultipleInterest(
            final @PathVariable("ids") List<Long> ids
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.of("", "", interestService.removeInterests(ids))
        );
    }

}
