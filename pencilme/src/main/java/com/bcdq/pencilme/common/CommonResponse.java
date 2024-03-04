package com.bcdq.pencilme.common;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommonResponse<T> {

    private String responseCode;
    private String responseMessage;
    private T data;

    @Builder(access = AccessLevel.PRIVATE)
    private CommonResponse(String responseCode, String responseMessage, T data) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.data = data;
    }

    public static CommonResponse<String> from(ResponseType responseType) {
        return CommonResponse.<String>builder()
                .responseCode(responseType.getResponseCode())
                .responseMessage(responseType.getResponseMessage())
                .data("")
                .build();
    }
    public static <T> CommonResponse<T> of (ResponseType responseType, T data) {
        return CommonResponse.<T>builder()
                .responseCode(responseType.getResponseCode())
                .responseMessage(responseType.getResponseMessage())
                .data(data)
                .build();
    }
}