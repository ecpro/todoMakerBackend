package com.piyush.todoMaker.model;

public enum Status {

    PENDING("PENDING"),
    COMPLETED("COMPLETED");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getValue() {
        return this.status;
    }

}
