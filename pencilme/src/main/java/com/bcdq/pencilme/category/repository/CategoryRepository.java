package com.bcdq.pencilme.category.repository;

import com.bcdq.pencilme.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
