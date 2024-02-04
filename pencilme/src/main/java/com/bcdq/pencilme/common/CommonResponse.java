package com.bcdq.pencilme.common;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    public static CommonResponse from (final String responseCode) {
        return CommonResponse.builder()
                .responseCode(responseCode)
                .build();
    }
    public static <T> CommonResponse<T> of (final String responseCode, final T data) {
        return CommonResponse.<T>builder()
                .responseCode(responseCode)
                .data(data)
                .build();
    }
}