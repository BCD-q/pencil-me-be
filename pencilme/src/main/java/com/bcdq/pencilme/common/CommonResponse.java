package com.bcdq.pencilme.common;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

/**
 * 표준 응답
 *
 * @author Juwon Lee
 */
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

    /**
     * 표준 응답을 위한 정적 팩터리 메서드 (data가 없는 경우)
     *
     * @param responseType 응답에 대한 Enum
     * @return ResponseEntity<CommonResponse<String>>> String 타입의 CommonResponse 응답
     */
    public static ResponseEntity<CommonResponse<String>> from (ResponseType responseType) {
        return ResponseEntity.status(responseType.getStatus())
                .body(CommonResponse.<String>builder()
                        .responseCode(responseType.getResponseCode())
                        .responseMessage(responseType.getResponseMessage())
                        .data("")
                        .build());
    }

    /**
     * 표준 응답을 위한 정적 팩터리 메서드 (data가 있는 경우)
     *
     * @param responseType 응답에 대한 Enum
     * @return ResponseEntity<CommonResponse<T>>> data 타입의 CommonResponse 응답
     */
    public static <T> ResponseEntity<CommonResponse<T>> of (ResponseType responseType, T data) {
        return ResponseEntity.status(responseType.getStatus())
                .body(CommonResponse.<T>builder()
                        .responseCode(responseType.getResponseCode())
                        .responseMessage(responseType.getResponseMessage())
                        .data(data)
                        .build());
    }
}