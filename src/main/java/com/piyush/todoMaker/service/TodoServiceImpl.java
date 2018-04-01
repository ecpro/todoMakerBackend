package com.piyush.todoMaker.service;

import com.piyush.todoMaker.exceptions.TodoException;
import com.piyush.todoMaker.model.Status;
import com.piyush.todoMaker.model.Todo;
import com.piyush.todoMaker.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Piyush Ravi
 * Service Class for TODOList
 */
@Service
public class TodoServiceImpl implements TodoService {

    Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);

    @Autowired
    private TodoRepository todoRepository;

    @PostConstruct
    private void init() {

        // initialize in-memory database with some initial values

        Todo vo1 = new Todo();
        vo1.setTodo("Buy Milk");
        vo1.setStatus(Status.PENDING);

        Todo vo2 = new Todo();
        vo2.setTodo("Recharge Phone");
        vo2.setStatus(Status.COMPLETED);

        todoRepository.save(vo1);
        todoRepository.save(vo2);

        logger.info("embedded mongo initialized successfully");

    }

    @Override
    public Todo updateStatus(String id, Todo todo) throws TodoException {
        Todo foundTodo = todoRepository.findById(id).orElse(null);
        if(foundTodo == null) throw new TodoException("Todo task not found");
        else {
            foundTodo.setStatus(todo.getStatus());
            foundTodo.setTodo(todo.getTodo());
            todoRepository.save(foundTodo);
            logger.info("Todo updated corresponding to id " + id + " with details " + todo);
        }
        return todo;
    }

    @Override
    public List<Todo> getAllTasks() {
        List<Todo> todoList = new ArrayList<>();
        todoRepository.findAll().forEach(todoList::add);
        return todoList;
    }

    @Override
    public Todo getById(String id) throws TodoException {
        Todo todo = todoRepository.findById(id).orElse(null);
        if(todo == null) throw new TodoException("Todo task not found");
        else return todo;
    }

    @Override
    public Todo saveOrUpdate(Todo todo) {
        todo.setId(null);
        return todoRepository.save(todo);
    }

    @Override
    public void delete(String id) {
        todoRepository.deleteById(id);
    }
}
