package com.bcdq.pencilme.todo.service;

import com.bcdq.pencilme.category.domain.Category;
import com.bcdq.pencilme.category.repository.CategoryRepository;
import com.bcdq.pencilme.member.domain.Member;
import com.bcdq.pencilme.member.repository.MemberRepository;
import com.bcdq.pencilme.todo.domain.Todo;
import com.bcdq.pencilme.todo.dto.request.CreateTodoRequest;
import com.bcdq.pencilme.todo.dto.request.UpdateTodoRequest;
import com.bcdq.pencilme.todo.dto.response.TodoResponse;
import com.bcdq.pencilme.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    public TodoResponse createTodo(CreateTodoRequest createTodoRequest, Member currentMember) {
        Category category = findByCategoryId(createTodoRequest.getCategoryId());
        Todo todo = CreateTodoRequest.toEntity(createTodoRequest, currentMember, category);
        todoRepository.save(todo);
        return TodoResponse.from(todo);
    }

    public TodoResponse readTodo(Long todoId) {
        return TodoResponse.from(findByTodoId(todoId));
    }

    public List<TodoResponse> readTodolist() {
        return todoRepository.findAll().stream()
                .map(TodoResponse::from)
                .collect(Collectors.toList());
    }

    public TodoResponse updateTodo(Long todoId, UpdateTodoRequest updateTodoRequest) {
        Todo todo = findByTodoId(todoId);
        todo.updateTodo(updateTodoRequest.getTitle(), updateTodoRequest.getContents(), updateTodoRequest.getDeadline(), updateTodoRequest.getIsFinished());
        todoRepository.save(todo);
        return TodoResponse.from(todo);
    }

    public void deleteTodo(Long todoId) {
        todoRepository.deleteById(todoId);
    }

    private Todo findByTodoId(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    private Category findByCategoryId(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(RuntimeException::new);
    }
}
