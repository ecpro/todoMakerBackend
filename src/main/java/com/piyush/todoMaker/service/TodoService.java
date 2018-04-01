package com.piyush.todoMaker.service;

import com.piyush.todoMaker.exceptions.TodoException;
import com.piyush.todoMaker.model.Todo;


import java.util.List;

public interface TodoService {

    List<Todo> getAllTasks();
    Todo getById(String id) throws TodoException;
    Todo saveOrUpdate(Todo todoVO);
    Todo updateStatus(String id, Todo todo) throws TodoException;
    void delete(String id);
}
