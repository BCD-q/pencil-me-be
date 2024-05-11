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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 그룹 관련 Service
 * 그룹 조회(단건, 전체), 추가, 수정, 삭제 요청 처리
 *
 * @author Juwon Lee
 */
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    /**
     * 그룹 생성 메서드
     * 파라미터로 받은 그룹 요청과 사용자 정보를 기반으로 그룹를 생성합니다
     *
     * @param createCategoryRequest 생성할 그룹의 정보를 담은 요청 DTO
     * @param currentMember 현재 로그인한 사용자
     * @return CategoryResponse 그룹 응답 DTO
     */
    public CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest, Member currentMember) {
        Category category = CreateCategoryRequest.toEntity(createCategoryRequest, currentMember);
        List<Category> categoryList = categoryRepository.findAll();
        Optional<Category> foundCategory = categoryList.stream().filter(entity -> Objects.equals(entity.getName(), category.getName())).findAny();
        if (foundCategory.isEmpty()) {
            categoryRepository.save(category);
            return CategoryResponse.from(category);
        } else {
            return CategoryResponse.from(foundCategory.get());
        }
    }

    /**
     * 그룹 단건 조회 메서드
     *
     * @param categoryId 조회할 그룹의 id 값
     * @return CategoryResponse 그룹 응답 DTO
     */
    public CategoryResponse readCategory(Long categoryId) {
        return CategoryResponse.from(findById(categoryId));
    }

    /**
     * 그룹 전체 조회 메서드
     *
     * @return List<CategoryResponse> 그룹 응답 DTO를 담은 List
     */
    public List<CategoryResponse> readCategoryList() {
        return categoryRepository.findAll().stream()
                .map(CategoryResponse::from)
                .collect(Collectors.toList());
    }

    /**
     * 그룹 수정 메서드
     *
     * @param categoryId 수정할 그룹의 id 값
     * @param updateCategoryRequest 수정할 그룹의 정보를 담은 요청 DTO
     * @return CategoryResponse 그룹 응답 DTO
     */
    public CategoryResponse updateCategory(Long categoryId, UpdateCategoryRequest updateCategoryRequest) {
        Category category = findById(categoryId);
        category.updateCategory(updateCategoryRequest.getName());
        categoryRepository.save(category);
        return CategoryResponse.from(category);
    }

    /**
     * 그룹 삭제 메서드
     *
     * @param categoryId 삭제할 그룹의 id 값
     */
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    /**
     * 그룹 DB 조회 메서드
     *
     * @param categoryId 조회할 그룹의 id 값
     * @return Category 조회된 그룹
     */
    private Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(RuntimeException::new);
    }
}
