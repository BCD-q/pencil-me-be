package com.bcdq.pencilme.category.dto.request;

import com.bcdq.pencilme.category.domain.Category;
import com.bcdq.pencilme.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCategoryRequest {

    @NotBlank
    @Schema(description = "카테고리의 이름을 입력해주세요", example = "Computer Science")
    private String name;

    public static Category toEntity(CreateCategoryRequest createCategoryRequest, Member member) {
        return Category.builder()
                .member(member)
                .name(createCategoryRequest.getName())
                .build();
    }
}
