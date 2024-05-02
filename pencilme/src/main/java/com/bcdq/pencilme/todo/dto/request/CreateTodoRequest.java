package com.bcdq.pencilme.todo.dto.request;

import com.bcdq.pencilme.category.domain.Category;
import com.bcdq.pencilme.member.domain.Member;
import com.bcdq.pencilme.todo.domain.Todo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 할 일 생성 요청을 위한 DTO
 *
 * @author Juwon Lee
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateTodoRequest {
    @NotBlank
    @Schema(description = "생성할 할 일의 제목", example = "캡디 발표")
    private String title;

    @NotBlank
    @Schema(description = "생성할 할 일의 내용", example = "키노트로 발표")
    private String contents;

    @NotNull
    @Schema(description = "생성할 할 일을 담을 그룹 식별자", example = "1")
    private Long categoryId;

    @NotNull
    @Schema(description = "생성할 할 일의 마감기한", example = "2024-06-01")
    private LocalDateTime deadline;

    @NotNull
    @Schema(description = "생성할 할 일의 완료 여부", example = "false")
    private Boolean isFinished;

    /**
     * 할 일 생성을 위한 정적 팩터리 메서드
     *
     * @param createTodoRequest 할 일 생성 요청을 위한 DTO
     * @param member 현재 로그인한 회원
     * @param category 할 일의 그룹
     * @return Todo 할 일 인스턴스
     */
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
