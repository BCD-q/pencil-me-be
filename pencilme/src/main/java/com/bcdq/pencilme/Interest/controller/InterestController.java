package com.bcdq.pencilme.Interest.controller;

import com.bcdq.pencilme.Interest.domain.dto.request.InterestReqDto;
import com.bcdq.pencilme.Interest.service.InterestService;
import com.bcdq.pencilme.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class InterestController {
    private final InterestService interestService;

    @PostMapping("/v1/interest")
    public ResponseEntity<CommonResponse<Void>> creatingMultipleInterests(
            final @RequestBody InterestReqDto.CreateInterests createInterests
    ) {
        interestService.createInterests(createInterests);
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.of("", "", null)
        );
    }

}
