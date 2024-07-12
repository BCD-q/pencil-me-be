package com.bcdq.pencilme.analysis.controller;

import com.bcdq.pencilme.analysis.dto.response.AnalysisResponse;
import com.bcdq.pencilme.analysis.service.AnalysisService;
import com.bcdq.pencilme.common.CommonResponse;
import com.bcdq.pencilme.member.domain.Member;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.bcdq.pencilme.common.ResponseType.목표점검;

@Tag(name = "[Analysis] 목표 점검 API", description = "할 일의 목표를 점검할 수 있는 API들의 모음입니다.")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AnalysisController {

    private final AnalysisService analysisService;

    @GetMapping("/v1/anaylsis")
    public ResponseEntity<CommonResponse<List<AnalysisResponse>>> getAnalysis(@AuthenticationPrincipal Member currentMember) {
        List<AnalysisResponse> analysisResponse = analysisService.readAnalysis(currentMember);
        return CommonResponse.of(목표점검, analysisResponse);
    }
}
