package com.greenfox.todo.controller;

import com.greenfox.todo.model.Todo;
import com.greenfox.todo.repository.TodoRepository;
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
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
}