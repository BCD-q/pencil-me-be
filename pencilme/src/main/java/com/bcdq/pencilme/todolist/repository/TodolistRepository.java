package com.bcdq.pencilme.todolist.repository;

import com.bcdq.pencilme.todolist.domain.Todolist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodolistRepository extends JpaRepository<Todolist, Long> {
}
