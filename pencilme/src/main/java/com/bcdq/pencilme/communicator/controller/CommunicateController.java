package com.bcdq.pencilme.communicator.controller;

import com.bcdq.pencilme.communicator.dto.request.TodoRequest;
import com.bcdq.pencilme.communicator.dto.response.CommunicateSummaryResponse;
import com.bcdq.pencilme.communicator.dto.response.CommunicateTodoResponse;
import com.bcdq.pencilme.communicator.service.CommunicateService;
import com.bcdq.pencilme.common.CommonResponse;
import com.bcdq.pencilme.member.domain.Member;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.io.IOException;

import static com.bcdq.pencilme.common.ResponseType.*;

/**
 * 서버 간 통신 관련 Controller
 * 할 일 등록 요청
 *
 * @author Juwon Lee
 */
@Tag(name = "[Communicator] FastAPI 연동 API", description = "FastAPI로 구현된 AI 기능을 사용할 수 있는 API들의 모음입니다.")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommunicateController {
    private final CommunicateService communicateService;

    /**
     * POST /api/v1/language
     *
     * @param todoRequest 등록할 할 일 내용
     * @return CommonResponse<CommunicateTodoResponse> 기본 응답 + 생성된 할 일 응답 DTO
     */
    @PostMapping("/v1/language")
    @Operation(summary = "할 일 등록 요청", description = "할 일 등록을 위해 AI Server에 전송할 할 일을 입력해주세요")
    public ResponseEntity<CommonResponse<CommunicateTodoResponse>> requestTodo(@Valid @RequestBody TodoRequest todoRequest, @AuthenticationPrincipal Member currentMember) throws JsonProcessingException {
        Long memberId = currentMember.getId();
        CommunicateTodoResponse communicateTodoResponse = communicateService.createTodo(todoRequest, memberId);
        return CommonResponse.of(할일등록요청완료, communicateTodoResponse);
    }

    /**
     * GET /api/v1/summarize
     *
     * @param url 요약할 url 링크
     * @return CommonResponse<CommunicateSummaryResponse> 기본 응답 + 생성된 요약문 응답 DTO
     */
    @GetMapping("/v1/summary")
    public ResponseEntity<CommonResponse<CommunicateSummaryResponse>> requestSummary(@RequestParam String url) throws IOException {
        CommunicateSummaryResponse communicateSummaryResponse = communicateService.readSummary(url);
        return CommonResponse.of(요약완료, communicateSummaryResponse);
    }
}
