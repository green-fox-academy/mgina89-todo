package com.greenfox.todo.repository;

import com.greenfox.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
  Optional<Todo> findByTitle(String title);
}
