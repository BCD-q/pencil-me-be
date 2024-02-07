package com.bcdq.pencilme.category.service;

import com.bcdq.pencilme.category.domain.Category;
import com.bcdq.pencilme.category.dto.request.CreateCategoryRequest;
import com.bcdq.pencilme.category.dto.request.UpdateCategoryRequest;
import com.bcdq.pencilme.category.dto.response.CategoryResponse;
import com.bcdq.pencilme.category.repository.CategoryRepository;
import com.bcdq.pencilme.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 카테고리 관련 Service
 * 카테고리 조회(단건, 전체), 추가, 수정, 삭제 요청 처리
 *
 * @author Juwon Lee
 */
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    /**
     * 카테고리 생성 메서드
     * 파라미터로 받은 카테고리 요청과 사용자 정보를 기반으로 카테고리를 생성합니다
     *
     * @param createCategoryRequest 생성할 카테고리의 내용을 담은 요청 DTO
     * @param currentMember 현재 로그인한 사용자
     * @return CategoryResponse 카테고리 응답 DTO
     */
    public CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest, Member currentMember) {
        Category category = CreateCategoryRequest.toEntity(createCategoryRequest, currentMember);
        categoryRepository.save(category);
        return CategoryResponse.from(category);
    }

    /**
     * 카테고리 단건 조회 메서드
     *
     * @param categoryId 조회할 카테고리의 id 값
     * @return CategoryResponse 카테고리 응답 DTO
     */
    public CategoryResponse readCategory(Long categoryId) {
        return CategoryResponse.from(findById(categoryId));
    }

    /**
     * 카테고리 전체 조회 메서드
     *
     * @return List<CategoryResponse> 카테고리 응답 DTO를 담은 List
     */
    public List<CategoryResponse> readCategoryList() {
       return null;
    }

    /**
     * 카테고리 수정 메서드
     *
     * @param categoryId 수정할 카테고리의 id 값
     * @param updateCategoryRequest 수정할 카테고리의 내용을 담은 요청 DTO
     * @return CategoryResponse 카테고리 응답 DTO
     */
    public CategoryResponse updateCategory(Long categoryId, UpdateCategoryRequest updateCategoryRequest) {
        return null;
    }

    /**
     * 카테고리 삭제 메서드
     *
     * @param categoryId 삭제할 카테고리의 id 값
     */
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    /**
     * 카테고리 DB 조회 메서드
     *
     * @param categoryId 조회할 카테고리의 id 값
     * @return Category 조회된 카테고리
     */
    private Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(RuntimeException::new);
    }
}
