package com.bcdq.pencilme.communicator.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * AI 서버 요청을 통해 생성된 포스팅 요약 응답 DTO
 *
 * @author Juwon Lee
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommunicateSummaryResponse {
    private String title;
    private String contents;

    @Builder(access = AccessLevel.PRIVATE)
    private CommunicateSummaryResponse(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    /**
     * AI 서버 요청을 통해 생성된 포스팅 요약 응답 DTO 생성을 위한 정적 팩터리 메서드
     *
     * @param title 요약한 제목
     * @param contents 요약한 내용
     * @return ResponseEntity<CommonResponse<T>>> data 타입의 CommonResponse 응답
     */
    public static CommunicateSummaryResponse of(String title, String contents) {
        return CommunicateSummaryResponse.builder()
                .title(title)
                .contents(contents)
                .build();
    }
}
