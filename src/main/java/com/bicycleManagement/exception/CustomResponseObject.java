package com.bicycleManagement.exception;

import java.time.LocalDateTime;

public class CustomResponseObject {

    private LocalDateTime timestamp = LocalDateTime.now();

    private String message;

    public CustomResponseObject() {
    }

    public CustomResponseObject(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
