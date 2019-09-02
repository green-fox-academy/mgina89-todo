package com.greenfox.todo.service;

import com.greenfox.todo.model.Todo;
import com.greenfox.todo.repository.TodoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {

  @Mock
  private TodoRepository repository;

  @Test
  public void assert_findAll_returns_todo_list_from_repository() throws Exception {
    TodoService service = new TodoService(repository);
    final List<Todo> todoList = Arrays.asList(new Todo("go shopping"), new Todo("wash clothes"));
    when(repository.findAll()).thenReturn(todoList);

    assertEquals(todoList, service.findAll());
  }

  @Test
  public void assert_delete_invokes_repository_delete() throws Exception {
    TodoService service = new TodoService(repository);
    service.delete(1);
    verify(repository).deleteById(1L);
  }

  @Test
  public void assert_todo_is_saved_if_not_exists() throws Exception {
    TodoService service = new TodoService(repository);
    when(repository.findByTitle(any())).thenReturn(Optional.empty());
    final Todo todo = new Todo("sleep");
    service.save(todo);
    verify(repository).save(todo);
  }

  @Test
  public void assert_todo_is_updated_if_already_exists() throws Exception {
    TodoService service = new TodoService(repository);
    Todo todo = new Todo("sleep");
    Todo existing = new Todo("sleep");
    existing.setId(3);
    existing.setUrgent(true);
    Todo expectedToSave = new Todo("sleep");
    expectedToSave.setId(3);
    when(repository.findByTitle(anyString())).thenReturn(Optional.of(existing));
//    when(repository.findByTitle(anyString())).thenAnswer(invocation -> {
//      Todo todo = new Todo(invocation.getArgument(0));
//      todo.setId(3);
//      todo.setUrgent(true);
//      return todo;
//    });

    service.save(todo);
    verify(repository).save(expectedToSave);
  }

}