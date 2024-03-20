package com.bcdq.pencilme.interest.domain.dto.request;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class InterestReqDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CreateInterests {
        private List<String> keywords;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class RemoveInterests {
        private List<Long> interestIds;
    }
}
