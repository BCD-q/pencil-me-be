package com.bcdq.pencilme.todo.controller;

import com.bcdq.pencilme.common.CommonResponse;
import com.bcdq.pencilme.member.domain.Member;
import com.bcdq.pencilme.todo.dto.request.CreateTodoRequest;
import com.bcdq.pencilme.todo.dto.request.UpdateTodoRequest;
import com.bcdq.pencilme.todo.dto.response.TodoResponse;
import com.bcdq.pencilme.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.bcdq.pencilme.common.ResponseType.*;

/**
 * 할 일 관련 Controller
 * 할 일 조회, 생성, 수정, 삭제 요청
 *
 * @author Juwon Lee
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    /**
     * POST /api/v1/todos
     * 할 일 생성 메서드
     *
     * @param createTodoRequest 생성할 할 일의 내용을 담은 요청 DTO
     * @return CommonResponse<TodoResponse> 기본 응답 + 할 일 응답 DTO
     */
    @PostMapping("/v1/todos")
    @Operation(summary = "할 일 생성", description = "생성할 할 일의 정보를 Body에 담아서 보내주세요")
    public ResponseEntity<CommonResponse<TodoResponse>> addTodo(@Valid @RequestBody CreateTodoRequest createTodoRequest, @AuthenticationPrincipal Member currentMember) {
        TodoResponse todoResponse = todoService.createTodo(createTodoRequest, currentMember);
        return CommonResponse.of(할일생성, todoResponse);
    }

    /**
     * GET /api/v1/todos/:categoryId
     * 할 일 그룹으로 조회 메서드
     *
     * @param categoryId 조회할 그룹의 id 값
     * @return CommonResponse<List<TodoResponse>> 기본 응답 + 할 일 응답 DTO 리스트
     */
    @GetMapping("/v1/todos/{categoryId}")
    @Operation(summary = "할 일 조회", description = "조회할 그룹의 식별자를 PathVariable로 보내주세요")
    public ResponseEntity<CommonResponse<List<TodoResponse>>> getTodo(@PathVariable("categoryId") Long categoryId) {
        List<TodoResponse> todoResponse = todoService.readTodo(categoryId);
        return CommonResponse.of(할일조회, todoResponse);
    }

    /**
     * GET /api/v1/todos
     * 할 일 전체 조회 메서드
     *
     * @return CommonResponse<List<TodoResponse>> 기본 응답 + 할 일 응답 DTO 리스트
     */
    @GetMapping("/v1/todos")
    @Operation(summary = "할 일 전체 조회", description = "모든 할 일을 조회합니다")
    public ResponseEntity<CommonResponse<List<TodoResponse>>> getTodolist(@AuthenticationPrincipal Member CurrentMember) {
        List<TodoResponse> todoResponse = todoService.readTodolist(CurrentMember);
        return CommonResponse.of(할일조회, todoResponse);
    }

    /**
     * PUT /api/v1/todos/:todoId
     * 할 일 수정 메서드
     *
     * @param todoId 수정할 할 일의 id 값
     * @param updateTodoRequest 수정할 할 일의 내용을 담은 요청 DTO
     * @return CommonResponse<TodoResponse> 기본 응답 + 할 일 응답 DTO
     */
    @PutMapping("/v1/todos/{todoId}")
    @Operation(summary = "할 일 수정", description = "수정할 할 일의 식별자를 PathVariable로, 수정할 내용은 Body에 담아서 보내주세요")
    public ResponseEntity<CommonResponse<TodoResponse>> modifyTodo(@PathVariable("todoId") Long todoId, @Valid @RequestBody UpdateTodoRequest updateTodoRequest) {
        TodoResponse todoResponse = todoService.updateTodo(todoId, updateTodoRequest);
        return CommonResponse.of(할일수정, todoResponse);
    }

    /**
     * DELETE /api/v1/todos/:todoId
     * 할 일 삭제 메서드
     *
     * @param todoId 삭제할 할 일의 id 값
     * @return CommonResponse 기본 응답
     */
    @DeleteMapping("/v1/todos/{todoId}")
    @Operation(summary = "할 일 삭제", description = "삭제할 할 일의 식별자를 PathVariable로 보내주세요")
    public ResponseEntity<CommonResponse<String>> removeTodo(@PathVariable("todoId") Long todoId) {
        todoService.deleteTodo(todoId);
        return CommonResponse.from(할일삭제);
    }
}
