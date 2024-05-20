package com.bcdq.pencilme.todo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 할 일 수정 요청을 위한 DTO
 *
 * @author Juwon Lee
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateTodoRequest {

    @NotNull
    private Long categoryId;

    @NotBlank
    private String title;

    @NotBlank
    private String contents;

    @NotNull
    private LocalDateTime deadline;

    @NotNull
    private Boolean isFinished;

    @NotNull
    private Boolean isImportant;
}