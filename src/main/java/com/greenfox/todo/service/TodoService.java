package com.greenfox.todo.service;

import com.greenfox.todo.model.Todo;
import com.greenfox.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

  private final TodoRepository repository;

  public List<Todo> findAll() {
    return repository.findAll();
  }

  public void save(Todo todo) {
    Optional<Todo> optional = repository.findByTitle(todo.getTitle());
    optional.ifPresent(existing -> todo.setId(existing.getId()));
    repository.save(todo);
  }

  public void delete(long id) {
    repository.deleteById(id);
  }
}
