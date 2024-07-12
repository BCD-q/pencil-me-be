package com.bcdq.pencilme.communicator.dto.request;

import com.bcdq.pencilme.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommunicateSummaryRequest {
    private Long memberId;
    private String memberName;
    private String memberEmail;

    @Builder(access = AccessLevel.PRIVATE)
    private CommunicateSummaryRequest(Long memberId, String memberName, String memberEmail) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
    }

    public static CommunicateSummaryRequest of(Member member) {
        return CommunicateSummaryRequest.builder()
                .memberId(member.getId())
                .memberName(member.getNickname())
                .memberEmail(member.getEmail())
                .build();
    }
}
