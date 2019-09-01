package com.greenfox.todo.controller;

import com.greenfox.todo.model.Todo;
import com.greenfox.todo.repository.TodoRepository;
import com.greenfox.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

  private final TodoService service;

  @GetMapping("/list")
  public List<Todo> list() {
    return service.findAll();
  }

  @PutMapping
  public ResponseEntity<Void> save(@RequestBody Todo todo) {
    service.save(todo);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable long id) {
    service.delete(id);
    return ResponseEntity.ok().build();
  }
}
