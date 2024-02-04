package com.bcdq.pencilme.category.domain.dto.response;

import com.bcdq.pencilme.category.domain.Category;
import lombok.Builder;
import lombok.Getter;

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

    public static CategoryResponse from(Category category) {
        return CategoryResponse.builder()
                .categoryId(category.getId())
                .memberId(category.getMember().getId())
                .categoryName(category.getName())
                .build();
    }
}
