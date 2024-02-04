package com.bcdq.pencilme.member.domain;

import com.bcdq.pencilme.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uid;

    private String email;

    private String password;

    private String nickname;

    @Builder
    public Member(Long id, String uid, String email, String password, String nickname) {
        this.id = id;
        this.uid = uid;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}
