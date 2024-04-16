package com.bcdq.pencilme.interest.dto.request;

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
        private List<String> keywords;
}
