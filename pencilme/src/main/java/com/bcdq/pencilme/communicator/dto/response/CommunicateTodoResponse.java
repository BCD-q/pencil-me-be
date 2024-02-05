package com.bcdq.pencilme.communicator.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommunicateTodoResponse {
    private Long memberId;
    private Long categoryId;
    private String title;
    private String contents;
    private LocalDateTime deadline;

    @Builder
    public CommunicateTodoResponse(Long memberId, Long categoryId, String title, String contents, LocalDateTime deadline) {
        this.memberId = memberId;
        this.categoryId = categoryId;
        this.title = title;
        this.contents = contents;
        this.deadline = deadline;
    }
}