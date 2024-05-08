package com.bcdq.pencilme.todo.repository;

import com.bcdq.pencilme.category.domain.Category;
import com.bcdq.pencilme.member.domain.Member;
import com.bcdq.pencilme.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByCategory(Category category);
    List<Todo> findAllByMember(Member member);
}
