package com.bcdq.pencilme.common;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "응답 코드", example = "MEMBER_REGISTERED")
    private final String responseCode;

    @Schema(description = "응답 메시지", example = "회원 가입 완료")
    private final String responseMessage;

    @Schema(description = "응답 데이터(DTO)", example = "{ id = 1 ... }")
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