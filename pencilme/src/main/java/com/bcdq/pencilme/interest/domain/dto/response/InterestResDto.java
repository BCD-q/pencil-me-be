package com.bcdq.pencilme.interest.domain.dto.response;

import lombok.Builder;
import lombok.Getter;

public class InterestResDto {
    @Builder
    @Getter
    public static class findAllInterests {
        private Long id;
        private String keyword;
    }
}
