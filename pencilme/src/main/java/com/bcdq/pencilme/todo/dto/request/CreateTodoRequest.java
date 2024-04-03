package com.bcdq.pencilme.todo.dto.request;

import com.bcdq.pencilme.category.domain.Category;
import com.bcdq.pencilme.member.domain.Member;
import com.bcdq.pencilme.todo.domain.Todo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateTodoRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String contents;

    @NotBlank
    private String category;

    @NotNull
    private LocalDateTime deadline;

    @NotNull
    private Boolean isFinished;

    public static Todo toEntity(CreateTodoRequest createTodoRequest, Member member, Category category) {
        return Todo.builder()
                .member(member)
                .category(category)
                .title(createTodoRequest.getTitle())
                .contents(createTodoRequest.getContents())
                .deadline(createTodoRequest.getDeadline())
                .isFinished(createTodoRequest.getIsFinished())
                .build();
    }
}
