package com.bcdq.pencilme.interest_mapping.dto.response;

import com.bcdq.pencilme.interest_mapping.domain.InterestMapping;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 관심사 매핑 응답 DTO
 *
 * @author Wonjeong Kim
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InterestMappingResponse {
        private Long id;
        private String keyword;

        @Builder(access = AccessLevel.PRIVATE)
        private InterestMappingResponse(Long id, String keyword) {
            this.id = id;
            this.keyword = keyword;
        }

    /**
     * 관심사 매핑 응답 DTO 생성을 위한 정적 팩터리 메서드
     *
     * @param interestMapping 관심사 매핑 인스턴스
     * @return InterestMappingResponse 관심사 매핑 응답 DTO 인스턴스
     */
    public static InterestMappingResponse from(InterestMapping interestMapping) {
            return InterestMappingResponse.builder()
                    .id(interestMapping.getId())
                    .keyword(interestMapping.getInterest().getKeyword())
                    .build();
        }
}
