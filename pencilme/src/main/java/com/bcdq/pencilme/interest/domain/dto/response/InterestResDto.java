package com.bcdq.pencilme.interest.domain.dto.response;

import com.bcdq.pencilme.interest.domain.Interest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class InterestResDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class findInterest {
        private Long id;
        private String keyword;

        @Builder
        protected findInterest(Long id, String keyword) {
            this.id = id;
            this.keyword = keyword;
        }

        public static findInterest createResponse(Interest interest) {
            return new findInterest(interest.getId(), interest.getKeyword());
        }
    }
}
