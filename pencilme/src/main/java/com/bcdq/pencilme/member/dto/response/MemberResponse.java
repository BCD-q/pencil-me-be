package com.bcdq.pencilme.member.dto.response;

import com.bcdq.pencilme.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "회원의 id값(식별자)", example = "1")
    private final Long id;

    @Schema(description = "회원의 아이디", example = "tester")
    private final String uid;

    @Schema(description = "회원의 이메일", example = "tester@gmail.com")
    private final String email;

    @Schema(description = "회원의 닉네임", example = "개발시러")
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
