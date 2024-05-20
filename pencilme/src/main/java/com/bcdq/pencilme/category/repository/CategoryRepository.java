package com.bcdq.pencilme.category.repository;

import com.bcdq.pencilme.category.domain.Category;
import com.bcdq.pencilme.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByMember(Member member);
    boolean existsByNameAndMember(String name, Member member);
    Optional<Category> findByNameAndMember(String name, Member member);
}
