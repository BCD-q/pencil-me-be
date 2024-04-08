package com.bcdq.pencilme.interest_mapping.domain.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class InterestMappingResDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class findAllByMember {
        private Long id;
        private String name;

        @Builder
        private findAllByMember(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
