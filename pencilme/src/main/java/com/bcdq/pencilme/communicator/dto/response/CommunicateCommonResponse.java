package com.bcdq.pencilme.communicator.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommunicateCommonResponse<T> {
    private String msg;
    private String result;
    private T data;

    private CommunicateCommonResponse(String msg, String result, T data) {
        this.msg = msg;
        this.result = result;
        this.data = data;
    }
}
