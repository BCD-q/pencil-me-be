package com.bcdq.pencilme.interest.domain;

import com.bcdq.pencilme.interest.dto.response.InterestResponse;
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

    public void updateKeyword(String keyword) {
        this.keyword = keyword;
    }
}
