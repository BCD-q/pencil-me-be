package com.bcdq.pencilme.member.dto.request;

import com.bcdq.pencilme.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 회원 가입 요청을 위한 DTO
 *
 * @author Juwon Lee
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpMemberRequest {

    @NotBlank
    @Schema(description = "가입할 회원의 아이디", example = "tester")
    private String uid;

    @NotBlank
    @Email
    @Schema(description = "가입할 회원의 이메일", example = "tester@gmail.com")
    private String email;

    @NotBlank
    @Schema(description = "가입할 회원의 비밀번호", example = "tester!@#")
    private String password;

    @NotBlank
    @Schema(description = "가입할 회원의 닉네임", example = "개발시러")
    private String nickname;

    /**
     * 회원 가입을 위한 정적 팩터리 메서드
     *
     * @param signUpMemberRequest 생성할 회원의 내용을 담은 요청 DTO
     * @return Member 회원 인스턴스
     */
    public static Member toEntity(SignUpMemberRequest signUpMemberRequest) {
        return Member.builder()
                .uid(signUpMemberRequest.getUid())
                .email(signUpMemberRequest.getEmail())
                .password(signUpMemberRequest.getPassword())
                .nickname(signUpMemberRequest.getNickname())
                .build();
    }
}