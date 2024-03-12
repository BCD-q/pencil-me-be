package com.bcdq.pencilme.todo.dto.response;

import com.bcdq.pencilme.todo.domain.Todo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoResponse {
    private Long id;

    private Long categoryId;

    private Long memberId;

    private String title;

    private String contents;

    private LocalDateTime deadline;

    private Boolean isFinished;

    @Builder(access = AccessLevel.PRIVATE)
    private TodoResponse(Long id, Long categoryId, Long memberId, String title, String contents, LocalDateTime deadline, Boolean isFinished) {
        this.id = id;
        this.categoryId = categoryId;
        this.memberId = memberId;
        this.title = title;
        this.contents = contents;
        this.deadline = deadline;
        this.isFinished = isFinished;
    }

    public static TodoResponse from(Todo todo) {
        return TodoResponse.builder()
                .id(todo.getId())
                .categoryId(todo.getCategory().getId())
                .memberId(todo.getMember().getId())
                .title(todo.getTitle())
                .contents(todo.getContents())
                .deadline(todo.getDeadline())
                .isFinished(todo.getIsFinished())
                .build();
    }
}
