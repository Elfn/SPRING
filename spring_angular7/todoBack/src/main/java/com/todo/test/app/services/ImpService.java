package com.todo.test.app.services;

import com.todo.test.app.entities.Todo;
import com.todo.test.app.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Elimane on Feb, 2019, at 19:03
 */
public class ImpService implements IService{

    @Autowired
    TodoRepository repo;

    @Override
    public List<Todo> getAllTodos() {

        return (List<Todo>) repo.findAll();
    }
}
