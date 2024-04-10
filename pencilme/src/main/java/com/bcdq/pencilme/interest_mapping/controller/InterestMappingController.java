package com.bcdq.pencilme.interest_mapping.controller;

import com.bcdq.pencilme.common.CommonResponse;
import com.bcdq.pencilme.common.ResponseType;
import com.bcdq.pencilme.interest_mapping.service.InterestMappingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class InterestMappingController {
    private final InterestMappingService interestMappingService;

    @Operation(summary = "Member와 Interest간 매핑")
    @GetMapping("/v1/interest-mapping")
    public ResponseEntity<CommonResponse<Void>> relatingObject(
            @RequestParam("interests")List<Long> interests,
            @AuthenticationPrincipal UserDetails userDetails
            ) {
        String uId = userDetails.getUsername();
        interestMappingService.relatingObjects(uId, interests);
        return CommonResponse.of(ResponseType.여러관심사멤버매핑, null);
    }
}
