package com.todo.test.app.repositories;

import com.todo.test.app.entities.Todo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Elimane on Feb, 2019, at 19:02
 */
public interface TodoRepository extends CrudRepository<Todo,Long> {
}
