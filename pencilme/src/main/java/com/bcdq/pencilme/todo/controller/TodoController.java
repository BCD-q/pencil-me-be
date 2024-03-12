package com.bcdq.pencilme.todo.controller;

import com.bcdq.pencilme.common.CommonResponse;
import com.bcdq.pencilme.todo.dto.request.CreateTodoRequest;
import com.bcdq.pencilme.todo.dto.request.UpdateTodoRequest;
import com.bcdq.pencilme.todo.dto.response.TodoResponse;
import com.bcdq.pencilme.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.bcdq.pencilme.common.ResponseType.*;

/**
 * Todo 관련 Controller
 * 할일 조회, 생성, 수정, 삭제 요청
 *
 * @author Juwon Lee
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/v1/todos")
    public ResponseEntity<CommonResponse<TodoResponse>> addTodo(@RequestBody @Valid CreateTodoRequest createTodoRequest) {
        TodoResponse todoResponse = todoService.createTodo(createTodoRequest);
        return CommonResponse.of(할일생성, todoResponse);
    }

    @GetMapping("/v1/todos/{todoId}")
    public ResponseEntity<CommonResponse<TodoResponse>> getTodo(@PathVariable("todoId") Long todoId) {
        TodoResponse todoResponse = todoService.readTodo(todoId);
        return CommonResponse.of(할일조회, todoResponse);
    }

    @GetMapping("/v1/todos")
    public ResponseEntity<CommonResponse<List<TodoResponse>>> getTodolist() {
        List<TodoResponse> todoResponse = todoService.readTodolist();
        return CommonResponse.of(할일조회, todoResponse);
    }

    @PutMapping("/v1/todos/{todoId}")
    public ResponseEntity<CommonResponse<TodoResponse>> modifyTodo(@RequestBody @Valid UpdateTodoRequest updateTodoRequest, @PathVariable("todoId") Long todoId) {
        TodoResponse todoResponse = todoService.updateTodo(todoId, updateTodoRequest);
        return CommonResponse.of(할일수정, todoResponse);
    }

    @DeleteMapping("/v1/todos/{todoId}")
    public ResponseEntity<CommonResponse<String>> removeTodo(@PathVariable("todoId") Long todoId) {
        todoService.deleteTodo(todoId);
        return CommonResponse.from(할일삭제);
    }
}
