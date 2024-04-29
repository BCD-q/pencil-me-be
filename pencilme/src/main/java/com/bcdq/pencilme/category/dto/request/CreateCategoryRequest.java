package com.bcdq.pencilme.category.dto.request;

import com.bcdq.pencilme.category.domain.Category;
import com.bcdq.pencilme.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 그룹 생성 요청을 위한 DTO
 *
 * @author Juwon Lee
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCategoryRequest {

    @NotBlank
    @Schema(description = "생성할 그룹의 이름", example = "Computer Science")
    private String name;

    /**
     * 그룹 생성을 위한 정적 팩터리 메서드
     *
     * @param createCategoryRequest 생성할 그룹의 내용을 담은 요청 DTO
     * @param member 현재 회원 정보
     * @return Category 그룹 인스턴스
     */
    public static Category toEntity(CreateCategoryRequest createCategoryRequest, Member member) {
        return Category.builder()
                .member(member)
                .name(createCategoryRequest.getName())
                .build();
    }
}
