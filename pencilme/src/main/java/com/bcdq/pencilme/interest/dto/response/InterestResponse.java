package com.bcdq.pencilme.interest.dto.response;

import com.bcdq.pencilme.interest.domain.Interest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 관심사 응답 DTO
 *
 * @author Wonjeong Kim
 */
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

    /**
     * 관심사 응답 DTO 생성을 위한 정적 팩터리 메서드
     *
     * @param interest 관심사 인스턴스
     * @return InterestResponse 관심사 응답 DTO 인스턴스
     */
    public static InterestResponse from(Interest interest) {
        return InterestResponse.builder()
                .id(interest.getId())
                .keyword(interest.getKeyword())
                .build();
    }
}
