package com.greenfox.todo.controller;

import com.greenfox.todo.model.Todo;
import com.greenfox.todo.service.TodoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = { TodoController.class })
public class TodoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TodoService service;

//  @Test
//  public void assert_list_returns_string() throws Exception {
//    mockMvc.perform(get("/todo/list")).andExpect(content().string("This is my first Todo"));
//  }


  @Test
  public void assert_put_returns_2xx() throws Exception {
    mockMvc.perform(put("/todo")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content("{\"title\":\"go shopping\"}"))
            .andExpect(status().is2xxSuccessful());
    verify(service).save(new Todo("go shopping"));
  }

  @Test
  public void assert_list_returns_todos() throws Exception {
    List<Todo> todos = Arrays.asList(new Todo("go shopping"), new Todo("cut nails"));
    when(service.findAll()).thenReturn(todos);

    mockMvc.perform(get("/todo/list")).andExpect(matchAll(
            status().is2xxSuccessful(),
            jsonPath("$[0].title").value("go shopping"),
            jsonPath("$[1].title").value( "cut nails")
    ));
  }

  @Test
  public void assert_delete_deletes_todo_by_id() throws Exception {
    mockMvc.perform(delete("/todo/1")).andExpect(status().is2xxSuccessful());
    verify(service).delete(1);
  }

  @Test
  public void assert_todolist_returns_view() throws Exception {
    List<Todo> todos = Arrays.asList(new Todo("go shopping"), new Todo("cut nails"));
    when(service.findAll()).thenReturn(todos);

    mockMvc.perform(get("/todo/list").accept(MediaType.TEXT_HTML))
            .andExpect(status().is2xxSuccessful())
            .andExpect(content().contentType("text/html;charset=UTF8"))
            .andExpect(content().string(containsString("go shopping")))
            .andExpect(content().string(containsString("cut nails")));

  }
}