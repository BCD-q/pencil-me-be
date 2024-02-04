package com.bcdq.pencilme.ai.domain.dto.request;

import com.bcdq.pencilme.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
public class AITodoApiRequest {
    @NotBlank
    private Long memberId;

    @NotBlank
    private String memberName;

    @NotBlank
    private String memberEmail;

    @NotBlank
    private String memberStatement;

    @NotBlank
    private LocalDateTime requestedDate;

    @Builder
    private AITodoApiRequest(Long memberId, String memberName, String memberEmail, String memberStatement, LocalDateTime requestedDate) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberStatement = memberStatement;
        this.requestedDate = requestedDate;
    }

    public static AITodoApiRequest of(AITodoRequest aiTodoRequest, Member member) {
        return AITodoApiRequest.builder()
                .memberId(member.getId())
                .memberName(member.getNickname())
                .memberEmail(member.getEmail())
                .memberStatement(aiTodoRequest.getUserStatement())
                .requestedDate(LocalDateTime.now())
                .build();
    }
}
