package com.bcdq.pencilme.member.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberSignInResponse {
    private final String token;

    @Builder(access = AccessLevel.PRIVATE)
    private MemberSignInResponse(String token) {
        this.token = token;
    }

    public static MemberSignInResponse from(String token) {
        return new MemberSignInResponse(token);
    }
}
