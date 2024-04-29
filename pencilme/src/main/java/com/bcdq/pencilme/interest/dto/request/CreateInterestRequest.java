package com.bcdq.pencilme.interest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 관심사 생성 (여러개) 요청을 위한 DTO
 *
 * @author Wonjeong Kim
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateInterestRequest {
        @Schema(description = "추가할 키워드 (여러개)", example = "[\"DevOps\", \"CI/CD\", \"Docker\", \"K8s...\"]")
        private List<String> keywords;
}
