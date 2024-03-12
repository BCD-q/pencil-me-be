package com.bcdq.pencilme.todo.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateTodoRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String contents;

    @NotNull
    private LocalDateTime deadline;

    @NotNull
    private Boolean isFinished;
}