package com.bcdq.pencilme.interest.domain;

import com.bcdq.pencilme.interest.domain.dto.response.InterestResDto;
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

    public InterestResDto.findInterest createResponseDto() {
        return InterestResDto.findInterest.builder()
                .id(this.id)
                .keyword(this.keyword)
                .build();
    }
}
