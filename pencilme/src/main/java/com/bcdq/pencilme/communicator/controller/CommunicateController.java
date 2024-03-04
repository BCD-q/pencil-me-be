package com.bcdq.pencilme.communicator.controller;

import com.bcdq.pencilme.communicator.dto.request.TodoRequest;
import com.bcdq.pencilme.communicator.dto.response.CommunicateTodoResponse;
import com.bcdq.pencilme.communicator.service.CommunicateService;
import com.bcdq.pencilme.common.CommonResponse;
import com.bcdq.pencilme.member.domain.Member;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bcdq.pencilme.common.ResponseType.*;

/**
 * 서버 간 통신 관련 Controller
 * 할 일 등록 요청
 *
 * @author Juwon Lee
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommunicateController {
    private final CommunicateService communicateTodoService;

    /**
     * POST /api/v1/language
     *
     * @param todoRequest 등록할 할 일 내용
     * @return CommonResponse<CommunicateTodoResponse> 기본 응답 + 생성된 할 일 응답 DTO
     */
    @PostMapping("/v1/language")
    @Operation(summary = "할 일 등록 요청", description = "할 일 등록을 위해 AI Server에 전송할 할 일을 입력해주세요")
    public ResponseEntity<CommonResponse<CommunicateTodoResponse>> requestTodo(@RequestBody TodoRequest todoRequest) throws JsonProcessingException {
        /*
            현재 로그인 한 사용자의 식별자 값을 기반으로 진행 - AuthenticationPrincipal
            @AuthenticationPrincipal Member currentMember 추가 예정
        */
        Member currentMember = Member.builder()
                .id(1L)
                .uid("tester")
                .password("test1234")
                .email("tester@inu.com")
                .nickname("테스터")
                .build();

        CommunicateTodoResponse communicateTodoResponse = communicateTodoService.createTodo(todoRequest, currentMember);
        return ResponseEntity.status(할일등록요청완료.getStatus())
                .body(CommonResponse.of(할일등록요청완료, communicateTodoResponse));
    }
}
