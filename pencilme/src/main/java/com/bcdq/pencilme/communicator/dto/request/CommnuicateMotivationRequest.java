package com.bcdq.pencilme.communicator.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommnuicateMotivationRequest {
    private List<String> keyword;


    private CommnuicateMotivationRequest(List<String> keyword) {
        this.keyword = keyword;
    }

    public static CommnuicateMotivationRequest from(List<String> keyword) {
        return new CommnuicateMotivationRequest(keyword);
    }
}
