package com.bcdq.pencilme.category.domain;

import com.bcdq.pencilme.common.BaseTimeEntity;
import com.bcdq.pencilme.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 그룹 Entity
 *
 * @author Juwon Lee
 */
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
    public Category(Member member, String name) {
        this.member = member;
        this.name = name;
    }

    /**
     * 그룹 이름 업데이트 메서드
     * 그룹에서 수정 가능한 값(그룹 이름)을 수정합니다
     *
     * @param name 수정할 이름 값
     */
    public void updateCategory(String name) {
        this.name = name;
    }
}
