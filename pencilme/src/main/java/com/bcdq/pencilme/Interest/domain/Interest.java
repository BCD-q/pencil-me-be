package com.bcdq.pencilme.Interest.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "interest")
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "keyword", unique = true)
    private String keyword;

    public Interest(String keyword) {
        this.keyword = keyword;
    }

    public Interest updateKeywordByString(String keyword) {
        this.keyword = keyword;
        return this;
    }
}
