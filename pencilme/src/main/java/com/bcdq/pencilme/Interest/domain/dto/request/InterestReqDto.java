package com.bcdq.pencilme.Interest.domain.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class InterestReqDto {
    @Getter
    public static class CreateInterests {
            private List<String> interests;
    }

    @Getter
    public static class RemoveInterests {
            private List<Long> interests;
    }
}
