package com.bcdq.pencilme.communicator.dto.response;

import lombok.Getter;
import java.time.LocalDateTime;

/**
 * AI 서버 요청을 통해 생성된 할 일 응답 DTO
 *
 * @author Juwon Lee
 */
@Getter
public class CommunicateTodoResponse {
    private final Long memberId;
    private final Long categoryId;
    private final String title;
    private final String contents;
    private final LocalDateTime deadline;

    private CommunicateTodoResponse(Long memberId, Long categoryId, String title, String contents, LocalDateTime deadline) {
        this.memberId = memberId;
        this.categoryId = categoryId;
        this.title = title;
        this.contents = contents;
        this.deadline = deadline;
    }
}