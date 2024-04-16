package com.bcdq.pencilme.interest.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * 관심사 Entity
 *
 * @author Wonjeong Kim
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "keyword", unique = true)
    private String keyword;

    public Interest(String keyword) {
        this.keyword = keyword;
    }

    /**
     * 관심사 수정 메서드
     * 관심사에서 수정 가능한 값(키워드)를 수정합니다
     *
     * @param keyword 수정할 키워드 값
     */
    public void updateKeyword(String keyword) {
        this.keyword = keyword;
    }
}
