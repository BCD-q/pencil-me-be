package com.bcdq.pencilme.category.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class UpdateCategoryRequest {

    @NotBlank
    @Schema(description = "적용할 카테고리 이름", nullable = false, example = "Spring boot")
    private String name;
}
