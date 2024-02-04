package com.bcdq.pencilme.category.controller;

import com.bcdq.pencilme.category.domain.dto.request.CreateCategoryRequest;
import com.bcdq.pencilme.category.domain.dto.response.CategoryResponse;
import com.bcdq.pencilme.category.service.CategoryService;
import com.bcdq.pencilme.common.CommonResponse;
import com.bcdq.pencilme.member.domain.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/v1/categories")
    @Operation(summary = "카테고리 추가", description = "할 일의 필수 요소인 카테고리를 추가합니다")
    public ResponseEntity<CommonResponse<CategoryResponse>> addCategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
        /*
            현재 로그인 한 사용자의 식별자 값을 기반으로 진행 - AuthenticationPrincipal
            @AuthenticationPrincipal Member currentMember 추가 예정
        */
        Member currentMember = Member.builder()
                .id(1L)
                .uid("tester")
                .password("test1234")
                .email("tester@inu.ac.kr")
                .nickname("테스터")
                .build();

        CategoryResponse categoryResponse = categoryService.createCategory(createCategoryRequest, currentMember);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.of("CATEGORY_CREATED", "카테고리 생성 완료", categoryResponse));
    }

    @GetMapping("/v1/categories/{id}")
    public ResponseEntity<CommonResponse<CategoryResponse>> getCategory(@PathVariable("id") Long id) {
        CategoryResponse categoryResponse = null;
        return ResponseEntity.ok(CommonResponse.of("", "", categoryResponse));
    }

    @PatchMapping("/v1/categories/{id}")
    public ResponseEntity<CommonResponse<CategoryResponse>> modifyCategory(@PathVariable("id") Long id) {
        CategoryResponse categoryResponse = null;
        return ResponseEntity.ok(CommonResponse.of("", "", categoryResponse));
    }

    @DeleteMapping("/v1/categories/{id}")
    public ResponseEntity<CommonResponse<CategoryResponse>> removeCategory(@PathVariable("id") Long id) {
        CategoryResponse categoryResponse = null;
        return ResponseEntity.ok(CommonResponse.of("", "", categoryResponse));
    }
}
