package com.bcdq.pencilme.member.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 로그인을 위한 요청 DTO
 *
 * @author Juwon Lee
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignInMemberRequest {

    @NotBlank
    private String uid;

    @NotBlank
    private String password;
}
