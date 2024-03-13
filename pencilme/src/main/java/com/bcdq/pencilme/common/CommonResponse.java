package com.bcdq.pencilme.common;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class CommonResponse<T> {

    private final String responseCode;
    private final String responseMessage;
    private final T data;

    @Builder(access = AccessLevel.PRIVATE)
    private CommonResponse(String responseCode, String responseMessage, T data) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.data = data;
    }

    public static ResponseEntity<CommonResponse<String>> from (ResponseType responseType) {
        return ResponseEntity.status(responseType.getStatus())
                .body(CommonResponse.<String>builder()
                        .responseCode(responseType.getResponseCode())
                        .responseMessage(responseType.getResponseMessage())
                        .data("")
                        .build());
    }

    public static <T> ResponseEntity<CommonResponse<T>> of (ResponseType responseType, T data) {
        return ResponseEntity.status(responseType.getStatus())
                .body(CommonResponse.<T>builder()
                        .responseCode(responseType.getResponseCode())
                        .responseMessage(responseType.getResponseMessage())
                        .data(data)
                        .build());
    }
}