package com.bcdq.pencilme.interest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 관심사 수정 요청을 위한 DTO
 *
 * @author Wonjeong Kim
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateInterestRequest {
    @Schema(description = "수정할 키워드", example = "Spring Boot")
    private String keyword;
}
