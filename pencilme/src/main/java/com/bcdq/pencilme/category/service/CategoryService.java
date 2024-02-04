package com.bcdq.pencilme.category.service;

import com.bcdq.pencilme.category.domain.Category;
import com.bcdq.pencilme.category.domain.dto.request.CreateCategoryRequest;
import com.bcdq.pencilme.category.domain.dto.response.CategoryResponse;
import com.bcdq.pencilme.category.repository.CategoryRepository;
import com.bcdq.pencilme.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest, Member currentMember) {
        Category category = createCategoryRequest.toEntity(createCategoryRequest, currentMember);
        categoryRepository.save(category);
        return CategoryResponse.from(category);
    }
}
