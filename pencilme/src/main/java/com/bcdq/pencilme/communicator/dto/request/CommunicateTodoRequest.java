package com.bcdq.pencilme.communicator.dto.request;

import com.bcdq.pencilme.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * AI 서버에서 요구하는 형태의 할 일 생성 요청 DTO
 *
 * @author Juwon Lee
 */
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
    private String requestedDate;

    @Builder
    private CommunicateTodoRequest(Long memberId, String memberName, String memberEmail, String memberStatement, LocalDateTime requestedDate) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberStatement = memberStatement;
        this.requestedDate = requestedDate.toString();
    }

    /**
     * AI 서버에서 요구하는 형태의 할 일 생성 요청 DTO 형태로 인스턴스화하는 정적 팩터리 메서드
     *
     * @param todoRequest 할 일 생성 요청을 담은 DTO
     * @param member 현재 로그인 한 사용자
     * @return CommunicateTodoRequest AI 서버에서 요구하는 형태의 할 일 생성 요청 DTO
     */
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
