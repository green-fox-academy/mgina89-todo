package com.greenfox.todo.repository;

import com.greenfox.todo.model.Todo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:todo",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect"
})
public class TodoRepositoryTest {

  @Autowired
  private TodoRepository repository;

  @Test
  public void assert_todo_can_be_found_by_title() throws Exception {
    repository.save(new Todo("dish wash"));
    Optional<Todo> optionalTodo = repository.findByTitle("dish wash");
    assertTrue(optionalTodo.isPresent());
    assertEquals(1, optionalTodo.get().getId());
  }

}