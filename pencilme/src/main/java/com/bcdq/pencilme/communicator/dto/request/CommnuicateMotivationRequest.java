package com.bcdq.pencilme.communicator.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommnuicateMotivationRequest {
    private List<String> keyword;
}
