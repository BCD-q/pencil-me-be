package com.bcdq.pencilme.communicator.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommunicateMotivationResponse {
    private String title;
    private String link;
    private String thumbnail_url;
}
