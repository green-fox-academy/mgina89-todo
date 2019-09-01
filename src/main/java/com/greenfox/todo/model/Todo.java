package com.greenfox.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "todos", indexes = { @Index(columnList = "title") })
@Data
@NoArgsConstructor
public class Todo {

  @GeneratedValue
  @Id
  private long id;
  private String title;
  private boolean urgent;
  private boolean done;

  public Todo(String title) {
    this.title = title;
  }
}
