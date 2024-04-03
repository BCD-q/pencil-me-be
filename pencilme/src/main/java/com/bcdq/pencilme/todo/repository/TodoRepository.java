package com.bcdq.pencilme.todo.repository;

import com.bcdq.pencilme.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
