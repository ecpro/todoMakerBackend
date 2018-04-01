package com.piyush.todoMaker.resources;

import com.piyush.todoMaker.exceptions.ErrorResponse;
import com.piyush.todoMaker.exceptions.TodoException;
import com.piyush.todoMaker.model.Todo;
import com.piyush.todoMaker.service.TodoServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todoApp")
public class TODOResource {

    private static final Logger logger = LoggerFactory.getLogger(TODOResource.class);

    @Autowired
    private TodoServiceImpl todoServiceImpl;

    // GET list of all the tasks ----------------------------------------------
    @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Todo>> getTasks() {
        List<Todo> todoList = todoServiceImpl.getAllTasks();
        logger.info("Todo task successfully retrieved");
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }


    // GET a task with given id ----------------------------------------------
    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Todo> getTask(@PathVariable("id") String id) throws TodoException {
        Todo todo = todoServiceImpl.getById(id);
        logger.info("Todo task successfully retrieved");
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    // POST: add a task --------------------------------------------------------
    @CrossOrigin
    @RequestMapping(value = "/tasks", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> addTask(@RequestBody Todo vo) {
        Todo todo = todoServiceImpl.saveOrUpdate(vo);
        logger.info("New Task added to db " + todo);
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    // DELETE: delete a task -----------------------------------------------------
    @CrossOrigin
    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteTask(@PathVariable("id") String id) throws TodoException {
        todoServiceImpl.delete(id);
        logger.info("Item corresponding to id [" + id + "] deleted");
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    // PUT: update status of a task ----------------------------------------------
    @CrossOrigin
    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Todo> updateTask(@PathVariable("id") String id, @RequestBody Todo todo) throws TodoException {
        Todo todo1 = todoServiceImpl.updateStatus(id, todo);
        logger.info("Task status updated for id " + id + " with data " + todo);
        return new ResponseEntity<>(todo1, HttpStatus.ACCEPTED);
    }


    // ---------- EXCEPTION HANDLERS ---------------------------------------------

    @ExceptionHandler(TodoException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(TodoException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        logger.error("exception occurred : " + ex);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getMessage());
        logger.error("exception occurred : " + ex);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
