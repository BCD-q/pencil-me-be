package com.bcdq.pencilme.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 회원 수정 요청을 위한 DTO
 *
 * @author Juwon Lee
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateMemberRequest {

    @NotBlank
    @Schema(description = "수정할 회원의 아이디", example = "tester2")
    private String uid;

    @NotBlank
    @Email
    @Schema(description = "수정할 회원의 이메일", example = "tester2@gmail.com")
    private String email;

    @NotBlank
    @Schema(description = "수정할 회원의 비밀번호", example = "tester!@#$")
    private String password;

    @NotBlank
    @Schema(description = "수정할 회원의 닉네임", example = "개발조아")
    private String nickname;
}
