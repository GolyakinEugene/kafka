package com.suncj.demo.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Message {
    @NotNull
    private String message;

    public String getMessage() {
        return message;
    }

    Message() {}

    Message(String message) {
        this.message = message;
    }
}
