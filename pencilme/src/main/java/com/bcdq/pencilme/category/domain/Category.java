package com.bcdq.pencilme.category.domain;

import com.bcdq.pencilme.common.BaseTimeEntity;
import com.bcdq.pencilme.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 카테고리 Entity
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
    public Category(Long id, Member member, String name) {
        this.id = id;
        this.member = member;
        this.name = name;
    }

    /**
     * 카테고리 이름 업데이트 메서드
     * 카테고리에서 수정 가능한 값(카테고리 이름)을 수정합니다
     *
     * @param name
     */
    public void updateCategory(String name) {
        this.name = name;
    }
}
