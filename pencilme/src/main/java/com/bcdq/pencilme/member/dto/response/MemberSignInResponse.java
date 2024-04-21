package com.bcdq.pencilme.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

/**
 * 회원 로그인 응답 DTO
 *
 * @author Juwon Lee
 */
@Getter
public class MemberSignInResponse {
    @Schema(description = "회원의 Access Token", example = "header.payload.signature")
    private final String token;

    @Builder(access = AccessLevel.PRIVATE)
    private MemberSignInResponse(String token) {
        this.token = token;
    }

    /**
     * 회원 로그인 응답 DTO를 생성하기 위한 정적 팩터리 메서드
     *
     * @param token Access Token 값
     * @return MemberSignInResponse 회원 로그인 응답 DTO 인스턴스
     */
    public static MemberSignInResponse from(String token) {
        return new MemberSignInResponse(token);
    }
}
