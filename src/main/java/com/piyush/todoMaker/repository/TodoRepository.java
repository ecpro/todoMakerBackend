package com.piyush.todoMaker.repository;

import com.piyush.todoMaker.model.Todo;
import org.springframework.data.repository.CrudRepository;


public interface TodoRepository extends CrudRepository<Todo, String> {

}
