package com.bcdq.pencilme.communicator.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * AI 서버 요청을 통해 생성된 포스팅 요약 응답 DTO
 *
 * @author Juwon Lee
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommunicateSummaryResponse {

    @Schema(description = "요약한 포스팅의 제목", example = "토스뱅크의 코어뱅킹 MSA 전환")
    private String title;

    @Schema(description = "요약한 포스팅의 내용", example = "토스뱅크는 기존의 공급자 중심의 뱅킹 서비스를 고객 중심으로 변화시키기 위해 MSA로 코어뱅킹 시스템을 전환하였고, 이를 통해...")
    private String contents;

    private Long categoryId;

    private LocalDateTime deadline;

    @Builder(access = AccessLevel.PRIVATE)
    private CommunicateSummaryResponse(String title, String contents, Long categoryId, LocalDateTime deadline) {
        this.title = title;
        this.contents = contents;
        this.categoryId = categoryId;
        this.deadline = deadline;
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

    /**
     * AI 서버 요청을 통해 생성된 포스팅 요약 응답 DTO 생성을 위한 정적 팩터리 메서드
     *
     * @param title 요약한 제목
     * @param contents 요약한 내용
     * @return ResponseEntity<CommonResponse<T>>> data 타입의 CommonResponse 응답
     */
    public static CommunicateSummaryResponse of(String title, String contents, Long categoryId) {
        return CommunicateSummaryResponse.builder()
                .title(title)
                .contents(contents)
                .categoryId(categoryId)
                .deadline(LocalDateTime.now().plusDays(7))
                .build();
    }
}
