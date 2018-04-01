package com.piyush.todoMaker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author Piyush Ravi
 */

@Document
public class Todo {

    @JsonProperty
    private String todo;

    @JsonProperty
    @Id
    private String id;

    @JsonProperty
    private Status status;

    public String getTodo() {
        return this.todo;
    }

    public String getId() {
        return this.id;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return "TodoVO{" +
                "todo='" + todo + '\'' +
                ", id='" + id + '\'' +
                ", status=" + status +
                '}';
    }
}
