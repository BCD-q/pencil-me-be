package com.bcdq.pencilme.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "로그인할 회원의 아이디", example = "tester")
    private String uid;

    @NotBlank
    @Schema(description = "로그인할 회원의 비밀번호", example = "tester!@#")
    private String password;
}
