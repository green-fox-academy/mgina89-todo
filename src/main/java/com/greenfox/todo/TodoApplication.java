package com.greenfox.todo;

import com.greenfox.todo.model.Todo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class TodoApplication {

  public static void main(String[] args) {
    TimeZone.setDefault(TimeZone.getTimeZone("Europe/Budapest"));
    SpringApplication.run(TodoApplication.class, args);
  }

}
