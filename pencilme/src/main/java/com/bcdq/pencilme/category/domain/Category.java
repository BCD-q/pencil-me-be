package com.bcdq.pencilme.category.domain;

import com.bcdq.pencilme.common.BaseTimeEntity;
import com.bcdq.pencilme.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String name;

    @Builder
    public Category(Long id, Member member, String name) {
        this.id = id;
        this.member = member;
        this.name = name;
    }
}
