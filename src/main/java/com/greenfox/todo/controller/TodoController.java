package com.greenfox.todo.controller;

import com.greenfox.todo.model.Todo;
import com.greenfox.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

  private final TodoService service;

  @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody List<Todo> list() {
    return service.findAll();
  }

  @GetMapping(value = "/list", produces = MediaType.TEXT_HTML_VALUE)
  public String todoList(Model model) {
    model.addAttribute("todos", service.findAll());
    return "todolist";
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
