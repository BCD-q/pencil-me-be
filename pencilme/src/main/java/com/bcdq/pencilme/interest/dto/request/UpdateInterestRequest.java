package com.bcdq.pencilme.interest.dto.request;

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
    private String keyword;
}
