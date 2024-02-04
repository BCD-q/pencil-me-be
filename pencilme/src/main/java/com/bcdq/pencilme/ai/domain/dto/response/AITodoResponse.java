package com.bcdq.pencilme.ai.domain.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AITodoResponse {
    private Long memberId;
    private Long categoryId;
    private String title;
    private String contents;
    private LocalDateTime deadline;

    @Builder
    public AITodoResponse(Long memberId, Long categoryId, String title, String contents, LocalDateTime deadline) {
        this.memberId = memberId;
        this.categoryId = categoryId;
        this.title = title;
        this.contents = contents;
        this.deadline = deadline;
    }
}