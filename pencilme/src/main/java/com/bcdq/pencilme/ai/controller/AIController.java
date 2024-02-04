package com.bcdq.pencilme.ai.controller;

import com.bcdq.pencilme.ai.domain.dto.request.AITodoRequest;
import com.bcdq.pencilme.ai.domain.dto.response.AITodoResponse;
import com.bcdq.pencilme.ai.service.AIService;
import com.bcdq.pencilme.common.CommonResponse;
import com.bcdq.pencilme.member.domain.Member;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AIController {
    private final AIService aiService;

    @PostMapping("/v1/language")
    @Operation(summary = "일정 등록 요청", description = "일정 등록을 위해 AI Server에 전송할 할 일을 입력해주세요")
    public ResponseEntity<CommonResponse<?>> requestTodo(@RequestBody AITodoRequest aiRequest) throws JsonProcessingException {
        /*
            현재 로그인 한 사용자의 식별자 값을 기반으로 진행 - AuthenticationPrincipal
            @AuthenticationPrincipal Member currentMember 추가 예정
        */
        Member currentMember = Member.builder()
                .id(1L)
                .uid("tester")
                .password("test1234")
                .email("tester@inu.ac.kr")
                .nickname("테스터")
                .build();

        AITodoResponse aiResponse = aiService.createTodo(aiRequest, currentMember);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.of("일정 등록 요청 완료", "AI_REQUEST_SENT", aiResponse));
    }
}
