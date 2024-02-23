package com.bcdq.pencilme.communicator.dto.request;

import com.bcdq.pencilme.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
public class CommunicateTodoRequest {
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
    private CommunicateTodoRequest(Long memberId, String memberName, String memberEmail, String memberStatement, LocalDateTime requestedDate) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberStatement = memberStatement;
        this.requestedDate = requestedDate;
    }

    public static CommunicateTodoRequest of(TodoRequest todoRequest, Member member) {
        return CommunicateTodoRequest.builder()
                .memberId(member.getId())
                .memberName(member.getNickname())
                .memberEmail(member.getEmail())
                .memberStatement(todoRequest.getMemberDialog())
                .requestedDate(LocalDateTime.now())
                .build();
    }
}
