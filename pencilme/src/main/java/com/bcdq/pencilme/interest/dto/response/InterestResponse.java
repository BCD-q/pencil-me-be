package com.bcdq.pencilme.interest.dto.response;

import com.bcdq.pencilme.interest.domain.Interest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InterestResponse {
    private Long id;
    private String keyword;

    @Builder(access = AccessLevel.PRIVATE)
    private InterestResponse(Long id, String keyword) {
        this.id = id;
        this.keyword = keyword;
    }

    public static InterestResponse from(Interest interest) {
        return InterestResponse.builder()
                .id(interest.getId())
                .keyword(interest.getKeyword())
                .build();
    }
}
