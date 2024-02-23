package com.bcdq.pencilme.category.dto.response;

import com.bcdq.pencilme.category.domain.Category;
import lombok.Builder;
import lombok.Getter;

/**
 * 카테고리 응답 DTO
 *
 * @author Juwon Lee
 */
@Getter
public class CategoryResponse {
    private Long categoryId;

    private Long memberId;

    private String categoryName;

    @Builder
    private CategoryResponse(Long categoryId, Long memberId, String categoryName) {
        this.categoryId = categoryId;
        this.memberId = memberId;
        this.categoryName = categoryName;
    }

    /**
     * 카테고리 응답 DTO 생성을 위한 정적 팩터리 메서드
     *
     * @param category 카테고리 인스턴스
     * @return CategoryResponse 카테고리 응답 DTO 인스턴스
     */
    public static CategoryResponse from(Category category) {
        return CategoryResponse.builder()
                .categoryId(category.getId())
                .memberId(category.getMember().getId())
                .categoryName(category.getName())
                .build();
    }
}
