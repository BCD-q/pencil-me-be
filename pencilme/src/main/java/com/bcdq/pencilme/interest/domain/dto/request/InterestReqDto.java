package com.bcdq.pencilme.interest.domain.dto.request;


import lombok.Getter;

import java.util.List;

public class InterestReqDto {
    @Getter
    public static class CreateInterests {
        private List<String> keywords;
    }

    @Getter
    public static class RemoveInterests {
        private List<Long> interestIds;
    }
}
