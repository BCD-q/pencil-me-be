package com.bcdq.pencilme.category.repository;

import com.bcdq.pencilme.category.domain.Category;
import com.bcdq.pencilme.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
