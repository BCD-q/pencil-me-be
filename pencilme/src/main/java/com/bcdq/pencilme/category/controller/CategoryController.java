package com.bcdq.pencilme.category.controller;

import com.bcdq.pencilme.category.dto.request.CreateCategoryRequest;
import com.bcdq.pencilme.category.dto.request.UpdateCategoryRequest;
import com.bcdq.pencilme.category.dto.response.CategoryResponse;
import com.bcdq.pencilme.category.service.CategoryService;
import com.bcdq.pencilme.common.CommonResponse;
import com.bcdq.pencilme.member.domain.Member;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.bcdq.pencilme.common.ResponseType.*;

/**
 * 그룹 관련 Controller
 * 그룹 조회(단건, 전체), 추가, 수정, 삭제 요청
 *
 * @author Juwon Lee
 */
@CrossOrigin // temp
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * POST /api/v1/categories
     * 그룹 생성 메서드
     *
     * @param createCategoryRequest 생성할 그룹의 내용을 담은 요청 DTO
     * @return CommonResponse<CategoryResponse> 기본 응답 + 그룹 응답 DTO
     */
    @PostMapping("/v1/categories")
    @Operation(summary = "그룹 생성", description = "생성할 그룹의 정보를 Body에 담아서 보내주세요")
    public ResponseEntity<CommonResponse<CategoryResponse>> addCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest, @AuthenticationPrincipal Member currentMember) {
        CategoryResponse categoryResponse = categoryService.createCategory(createCategoryRequest, currentMember);
        return CommonResponse.of(그룹생성, categoryResponse);
    }

    /**
     * GET /api/v1/categories/:categoryId
     * 그룹 단건 조회 메서드
     *
     * @param categoryId 조회할 그룹의 id 값
     * @return CommonResponse<CategoryResponse> 기본 응답 + 그룹 응답 DTO
     */
    @GetMapping("/v1/categories/{categoryId}")
    @Operation(summary = "그룹 단건 조회", description = "조회할 그룹의 식별자를 PathVariable로 보내주세요")
    public ResponseEntity<CommonResponse<CategoryResponse>> getCategory(@PathVariable("categoryId") Long categoryId) {
        CategoryResponse categoryResponse = categoryService.readCategory(categoryId);
        return CommonResponse.of(그룹조회, categoryResponse);
    }

    /**
     * GET /api/v1/categories
     * 그룹 전체 조회 메서드
     *
     * @return CommonResponse<List<CategoryResponse>> 기본 응답 + 그룹 응답 DTO 리스트
     */
    @GetMapping("/v1/categories")
    @Operation(summary = "그룹 전체 조회", description = "모든 그룹을 조회합니다")
    public ResponseEntity<CommonResponse<List<CategoryResponse>>> getCategory() {
        List<CategoryResponse> categoryResponse = categoryService.readCategoryList();
        return CommonResponse.of(그룹조회, categoryResponse);
    }

    /**
     * PUT /api/v1/categories/:categoryId
     * 그룹 수정 메서드
     *
     * @param categoryId 수정할 그룹의 id 값
     * @param updateCategoryRequest 수정할 그룹의 내용을 담은 요청 DTO
     * @return CommonResponse<CategoryResponse> 기본 응답 + 그룹 응답 DTO
     */
    @PutMapping("/v1/categories/{categoryId}")
    @Operation(summary = "그룹 수정", description = "수정할 그룹의 식별자를 PathVariable로, 수정할 내용은 Body에 담아서 보내주세요")
    public ResponseEntity<CommonResponse<CategoryResponse>> modifyCategory(@PathVariable("categoryId") Long categoryId, @Valid @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        CategoryResponse categoryResponse = categoryService.updateCategory(categoryId, updateCategoryRequest);
        return CommonResponse.of(그룹수정, categoryResponse);
    }

    /**
     * DELETE /api/v1/categories/:categoryId
     * 그룹 삭제 메서드
     *
     * @param categoryId 삭제할 그룹의 id 값
     * @return CommonResponse 기본 응답
     */
    @DeleteMapping("/v1/categories/{categoryId}")
    @Operation(summary = "그룹 삭제", description = "삭제할 그룹의 식별자를 PathVariable로 보내주세요")
    public ResponseEntity<CommonResponse<String>> removeCategory(@PathVariable("categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return CommonResponse.from(그룹삭제);
    }
}
