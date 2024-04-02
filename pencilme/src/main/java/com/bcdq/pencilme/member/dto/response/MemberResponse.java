package com.bcdq.pencilme.member.dto.response;

import com.bcdq.pencilme.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

/**
 * 회원 응답 DTO
 *
 * @author Juwon Lee
 */
@Getter
public class MemberResponse {
    private final Long id;
    private final String uid;
    private final String email;
    private final String nickname;

    @Builder(access = AccessLevel.PRIVATE)
    private MemberResponse(Long id, String uid, String email, String password, String nickname) {
        this.id = id;
        this.uid = uid;
        this.email = email;
        this.nickname = nickname;
    }

    /**
     * 회원 응답 DTO 생성을 위한 정적 팩터리 메서드
     *
     * @param member 회원 인스턴스
     * @return MemberResponse 회원 응답 DTO 인스턴스
     */
    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .uid(member.getUid())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }
}
