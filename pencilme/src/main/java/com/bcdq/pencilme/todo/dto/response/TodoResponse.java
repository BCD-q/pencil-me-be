package com.bcdq.pencilme.todo.dto.response;

import com.bcdq.pencilme.todo.domain.Todo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 할 일 응답 DTO
 *
 * @author Juwon Lee
 */
@Getter
public class TodoResponse {
    @Schema(description = "할 일의 id값", example = "1")
    private final Long id;

    @Schema(description = "그룹 id값", example = "1")
    private final Long categoryId;

    @Schema(description = "그룹명", example = "컴퓨터")
    private final String categoryName;

    @Schema(description = "회원 id값", example = "1")
    private final Long memberId;

    @Schema(description = "할 일의 제목", example = "캡디 발표")
    private final String title;

    @Schema(description = "할 일의 내용", example = "키노트로 발표합니다")
    private final String contents;

    @Schema(description = "마갑기한", example = "2024-06-01")
    private final LocalDateTime deadline;

    @Schema(description = "완료 여부", example = "false")
    private final Boolean isFinished;

    @Schema(description = "중요 여부", example = "false")
    private final Boolean isImportant;

    @Builder(access = AccessLevel.PRIVATE)
    private TodoResponse(Long id, Long categoryId, String categoryName, Long memberId, String title, String contents, LocalDateTime deadline, Boolean isFinished, Boolean isImportant) {
        this.id = id;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.memberId = memberId;
        this.title = title;
        this.contents = contents;
        this.deadline = deadline;
        this.isFinished = isFinished;
        this.isImportant = isImportant;
    }

    public static TodoResponse from(Todo todo) {
        return TodoResponse.builder()
                .id(todo.getId())
                .categoryId(todo.getCategory().getId())
                .categoryName(todo.getCategory().getName())
                .memberId(todo.getMember().getId())
                .title(todo.getTitle())
                .contents(todo.getContents())
                .deadline(todo.getDeadline())
                .isFinished(todo.getIsFinished())
                .isImportant(todo.getIsImportant())
                .build();
    }
}
