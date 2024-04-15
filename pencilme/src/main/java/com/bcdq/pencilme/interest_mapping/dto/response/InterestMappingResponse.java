package com.bcdq.pencilme.interest_mapping.dto.response;

import com.bcdq.pencilme.interest_mapping.domain.InterestMapping;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

        public static InterestMappingResponse from(InterestMapping interestMapping) {
            return InterestMappingResponse.builder()
                    .id(interestMapping.getId())
                    .keyword(interestMapping.getInterest().getKeyword())
                    .build();
        }
}
