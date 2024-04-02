package com.bcdq.pencilme.member.dto.request;

import com.bcdq.pencilme.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String uid;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
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