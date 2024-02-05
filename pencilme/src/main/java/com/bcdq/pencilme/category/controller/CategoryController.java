package com.bcdq.pencilme.category.controller;

import com.bcdq.pencilme.category.dto.request.CreateCategoryRequest;
import com.bcdq.pencilme.category.dto.response.CategoryResponse;
import com.bcdq.pencilme.category.service.CategoryService;
import com.bcdq.pencilme.common.CommonResponse;
import com.bcdq.pencilme.member.domain.Member;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bcdq.pencilme.common.ResponseType.*;

/**
 * 카테고리 관련 Controller
 * 카테고리 조회(1개, 전체), 추가, 수정, 삭제 요청
 *
 * @author Juwon Lee
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * POST /api/v1/categories
     * 카테고리 생성 메서드
     *
     * @param createCategoryRequest
     * @return CommonResponseDto<CategoryResponse>
     */
    @PostMapping("/v1/categories")
    @Operation(summary = "카테고리 생성", description = "할 일의 필수 요소인 카테고리를 생성합니다")
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
        return ResponseEntity.status(카테고리생성.getStatus())
                .body(CommonResponse.of(카테고리생성.getResponseCode(), 카테고리생성.getResponseMessage(), categoryResponse));
    }

    /**
     * GET /api/v1/categories/:categoryId
     * 카테고리 단건 조회 메서드
     *
     * @param categoryId
     * @return CommonResponseDto<CategoryResponse>
     */
    @GetMapping("/v1/categories/{categoryId}")
    @Operation(summary = "카테고리 단건 조회", description = "할 일에 대한 카테고리를 조회합니다")
    public ResponseEntity<CommonResponse<CategoryResponse>> getCategory(@PathVariable("categoryId") Long categoryId) {
        CategoryResponse categoryResponse = null;
        return ResponseEntity.status(카테고리조회.getStatus())
                .body(CommonResponse.of(카테고리조회.getResponseCode(), 카테고리조회.getResponseMessage(), categoryResponse));
    }

    /**
     * GET /api/v1/categories
     * 카테고리 전체 조회 메서드
     *
     * @param
     * @return CommonResponseDto<CategoryResponse>
     */
    @GetMapping("/v1/categories")
    @Operation(summary = "카테고리 전체 조회", description = "할 일에 대한 모든 카테고리를 조회합니다")
    public ResponseEntity<CommonResponse<CategoryResponse>> getCategory() {
        CategoryResponse categoryResponse = null;
        return ResponseEntity.status(카테고리조회.getStatus())
                .body(CommonResponse.of(카테고리조회.getResponseCode(), 카테고리조회.getResponseMessage(), categoryResponse));
    }

    /**
     * PATCH /api/v1/categories/:categoryId
     * 카테고리 수정 메서드
     *
     * @param categoryId
     * @return CommonResponseDto<CategoryResponse>
     */
    @PatchMapping("/v1/categories/{categoryId}")
    @Operation(summary = "카테고리 수정", description = "할 일에 대한 카테고리의 내용을 수정합니다. 카테고리명을 수정할 수 있습니다.")
    public ResponseEntity<CommonResponse<CategoryResponse>> modifyCategory(@PathVariable("categoryId") Long categoryId) {
        CategoryResponse categoryResponse = null;
        return ResponseEntity.status(카테고리수정.getStatus())
                .body(CommonResponse.of(카테고리수정.getResponseCode(), 카테고리수정.getResponseMessage(), categoryResponse));
    }

    /**
     * DELETE /api/v1/categories/:categoryId
     * 카테고리 삭제 메서드
     *
     * @param categoryId
     * @return CommonResponseDto<CategoryResponse>
     */
    @DeleteMapping("/v1/categories/{categoryId}")
    @Operation(summary = "카테고리 삭제", description = "할 일에 대한 카테고리를 삭제합니다")
    public ResponseEntity<CommonResponse<CategoryResponse>> removeCategory(@PathVariable("categoryId") Long categoryId) {
        CategoryResponse categoryResponse = null;
        return ResponseEntity.status(카테고리삭제.getStatus())
                .body(CommonResponse.of(카테고리삭제.getResponseCode(), 카테고리삭제.getResponseMessage(), categoryResponse));
    }
}
